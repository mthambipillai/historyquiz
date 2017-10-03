/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Date;
import entity.ExactDate;
import entity.Name;
import entity.Period;
import entity.Player;
import entity.Question;
import entity.Score;
import entity.Tag;
import game.Quiz;
import game.Training;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mail.MailManager;
import session.DateFacade;
import session.ExactDateFacade;
import session.GroupsFacade;
import session.NameFacade;
import session.NewPlayerManager;
import session.NewScoresManager;
import session.PeriodFacade;
import session.PlayerFacade;
import session.ScoreFacade;
import session.TagCategoryFacade;
import session.TagFacade;
import validate.Validator;


/**
 *
 * @author Melkior
 */
@WebServlet(name = "ControllerServlet", loadOnStartup=1,
        urlPatterns = {
            "/allquestions",
            "/profile",
            "/changePseudoEmail",
            "/changePassword",
            "/passwordChanged",
            "/profile_saved",
            "/rankings",
            "/game",
            "/training",
            "/game_selection",
            "/unselectAllTags",
            "/game_end",
            "/login",
            "/error",
            "/logout",
            "/account_created",
            "/index",
            "/selectTagCategory",
            "/addTag",
            "/submitQuizAnswer",
            "/submitTrainingAnswer",
            "/createAccount",
            "/removeTag",
            "/chooseLanguage",
            "/contact",
            "/contact_sent",
            "/contact_not_sent",
            "/sendContactMail",
            "/notfound"
        })
public class ControllerServlet extends HttpServlet {
    
    public static final int registeredUserGroupId = 1;
    
    
    @EJB
    private TagFacade tags;
    @EJB
    private TagCategoryFacade tagsCategories;
    @EJB
    private DateFacade dates;
    @EJB
    private PeriodFacade periods;
    @EJB
    private ExactDateFacade exactDates;
    @EJB
    private NameFacade names;
    @EJB
    private ScoreFacade scores;
    @EJB
    private GroupsFacade groups;
    @EJB
    private PlayerFacade players;
    @EJB
    private NewPlayerManager npManager;
    @EJB
    private NewScoresManager nsManager;
    
    private Collection<Player> registeredUsers;
    
    private final Validator validator = new Validator();
    
    private final MailManager mailManager = new MailManager("smtp.netplus.ch");
    
    private ControllerActions actions = null;
    
    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("tags", tags.findAll());
        getServletContext().setAttribute("tagsCategories", tagsCategories.findAll());
        registeredUsers = groups.find(registeredUserGroupId).getPlayerCollection();
        actions = new ControllerActions(tags,tagsCategories,dates,periods,exactDates,names,scores,
                groups,players,npManager,nsManager,registeredUsers,validator,mailManager);
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        
        Tag selectedTag=null;
        TagCategoriesWrapper tagCategoriesWrapper = null;
        
        Collection<Date> selectedDates=null;
        Collection<Period> selectedPeriods=null;
        Collection<ExactDate> selectedExactDates=null;
        Collection<Name> selectedNames=null;
        
        ArrayList<Tag> gameSelectedTags=null;
        HashSet<Question> selectedQuestions=null;
        HashMap<Tag,ArrayList<Question>> selectedTagsQuestions=null;
        Quiz quiz=null;
        Training training=null;

        ArrayList<Score> selectedScores = new ArrayList<Score>();
        
        Collection<Score> playerScores=null;

        if (userPath.equals("/allquestions")) {
            
            userPath = actions.allquestionsActions(request,session,
                    selectedTag,selectedDates,selectedPeriods,selectedExactDates,selectedNames,tagCategoriesWrapper);
            
        } else if(userPath.equals("/selectTagCategory")){
            
            int categoryId = Integer.parseInt(request.getQueryString());
            tagCategoriesWrapper = (TagCategoriesWrapper) session.getAttribute("tagCategoriesWrapper");
            tagCategoriesWrapper.categorySelectedChange(categoryId);
            session.setAttribute("tagCategoriesWrapper", tagCategoriesWrapper);
            userPath = (String) session.getAttribute("view");
            
        }else if (userPath.equals("/profile")) {
            
            userPath = actions.profileActions(request,session,playerScores);
            
        }else if(userPath.equals("/changePassword")){
        
            session.setAttribute("changePasswordRequired", true);
            userPath = "/profile";
            
        } else if (userPath.equals("/rankings")) {
            
            userPath = actions.rankingsActions(request,session,selectedTag,selectedScores,tagCategoriesWrapper);
            
        } else if (userPath.equals("/game")) {
            
            userPath = actions.gameActions(request,session, quiz, gameSelectedTags,
                    selectedQuestions, selectedTagsQuestions);

        } else if (userPath.equals("/training")) {
            
            userPath = actions.trainingActions(request,session, training, gameSelectedTags,
                    selectedQuestions, selectedTagsQuestions);

        } else if (userPath.equals("/game_selection")) {

            session.setAttribute("answerSubmitted", false);

        } else if(userPath.equals("/unselectAllTags")){
            
            gameSelectedTags = new ArrayList<Tag>();
            session.setAttribute("gameSelectedTags", gameSelectedTags);
            userPath = "/game_selection";
            
        } else if(userPath.equals("/game_end")){
            
            userPath = actions.gameEndActions(quiz,gameSelectedTags,playerScores,
                    selectedQuestions,selectedTagsQuestions,request,session);
            
        } else if(userPath.equals("/logout")){
            session.invalidate();
            userPath = "/game_selection";
            
        } else if(userPath.equals("/chooseLanguage")){
            
            String language = request.getParameter("language");
            request.setAttribute("language", language);
            session.setAttribute("language", language);
            actions.setQuizLanguage(request, session, language);

            String userView = (String) session.getAttribute("view");

            if ((userView != null) &&
                (!userView.equals("/index"))) {     // index.jsp exists outside 'view' folder
                                                    // so must be forwarded separately
                userPath = userView;
            } else {

                // if previous view is index or cannot be determined, send user to welcome page
                try {
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }
        }
        
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        
        ArrayList<Tag> gameSelectedTags = (ArrayList<Tag>) session.getAttribute("gameSelectedTags");
        Quiz quiz=null;
        Training training=null;
        
        if(userPath.equals("/addTag")){
            
            userPath = actions.addTagActions(request,session,gameSelectedTags);
            
        } else if(userPath.equals("/submitQuizAnswer")){
            
            userPath = actions.submitQuizAnswerActions(quiz,request,session);
            
        }  else if(userPath.equals("/submitTrainingAnswer")){
            
            userPath = actions.submitTrainingAnswerActions(training,request,session);
            
        } else if(userPath.equals("/createAccount")){
            
            userPath = actions.createAccountActions(request,session);
            
        } else if(userPath.equals("/removeTag")){
            
            userPath = actions.removeTagActions(request,session,gameSelectedTags);
            
        } else if(userPath.equals("/passwordChanged")){
            
            userPath = actions.passwordChangedActions(request,session);
            
        } else if(userPath.equals("/changePseudoEmail")){
            
            userPath = actions.changePseudoEmailActions(request, session);
            
        }else if(userPath.equals("/sendContactMail")){
            
            userPath = actions.sendContactMailActions(request, session);
            
        }

        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}