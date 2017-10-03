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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mail.MailManager;
import session.*;
import validate.Validator;

/**
 *
 * @author Melkior
 */
public class ControllerActions {
    
    private int registeredUserGroupId = ControllerServlet.registeredUserGroupId;
    
    private TagFacade tags;
    private TagCategoryFacade tagsCategories;
    private DateFacade dates;
    private PeriodFacade periods;
    private ExactDateFacade exactDates;
    private NameFacade names;
    private ScoreFacade scores;
    private GroupsFacade groups;
    private PlayerFacade players;
    private NewPlayerManager npManager;
    private NewScoresManager nsManager;
    
    private Collection<Player> registeredUsers;
    
    private Validator validator = new Validator();
    
    private MailManager mailManager = new MailManager("smtp.netplus.ch");
    
    public ControllerActions(TagFacade tags,TagCategoryFacade tagsCategories,
            DateFacade dates,PeriodFacade periods,ExactDateFacade exactDates,NameFacade names,
            ScoreFacade scores,GroupsFacade groups,PlayerFacade players,NewPlayerManager npManager,
            NewScoresManager nsManager,Collection<Player> registeredUsers,Validator validator,MailManager mailManager){
        this.tags = tags;
        this.tagsCategories = tagsCategories;
        this.dates = dates;
        this.periods = periods;
        this.exactDates = exactDates;
        this.names = names;
        this.scores = scores;
        this.groups = groups;
        this.players = players;
        this.npManager = npManager;
        this.nsManager = nsManager;
        this.registeredUsers = registeredUsers;
        this.validator = validator;
        this.mailManager = mailManager;
    }
    
    public String submitQuizAnswerActions(Quiz quiz,HttpServletRequest request,HttpSession session){
        quiz = (Quiz) session.getAttribute("quiz");
        String answer = request.getParameter("answer");
        quiz.answer(answer);

        int currentQuizSize = (int) session.getAttribute("currentQuizSize");
        currentQuizSize--;
        session.setAttribute("currentQuizSize", currentQuizSize);
        session.setAttribute("answerSubmitted",true);
        return "/game";
    }
    public String submitTrainingAnswerActions(Training training,HttpServletRequest request,HttpSession session){
        training = (Training) session.getAttribute("training");
        String answer = request.getParameter("answer");
        training.answer(answer);
        session.setAttribute("answerSubmitted",true);
        return "/training";
    }
    
    public String gameActions(HttpServletRequest request,HttpSession session, Quiz quiz,
            ArrayList<Tag> gameSelectedTags,
            HashSet<Question> selectedQuestions,
            HashMap<Tag,ArrayList<Question>> selectedTagsQuestions){
        
        String query = request.getQueryString();
            
        quiz = (Quiz) session.getAttribute("quiz");

        if (query == null) {
            return firstGameActions(session, quiz, gameSelectedTags, selectedQuestions, selectedTagsQuestions);
        } else {
            if (query.equals("nextQuestion")) {
                session.setAttribute("answerSubmitted", false);
                int currentQuizSize = (int) session.getAttribute("currentQuizSize");
                if (currentQuizSize < 0) {
                    return "/game_end";
                } else {

                    quiz.askNextQuestion();
                    return "/game";
                }
            }else{
                return null;
            }
        }
    }
    
    public String trainingActions(HttpServletRequest request,HttpSession session, Training training,
            ArrayList<Tag> gameSelectedTags,
            HashSet<Question> selectedQuestions,
            HashMap<Tag,ArrayList<Question>> selectedTagsQuestions){
        
        String query = request.getQueryString();
            
        training = (Training) session.getAttribute("training");

        if (query == null) {
            return firstTrainingActions(session, training, gameSelectedTags, selectedQuestions);
        } else {
            if (query.equals("nextQuestion")) {
                session.setAttribute("answerSubmitted", false);
                training.askNextQuestion();
                return "/training";
            }else{
                return null;
            }
        }
    }
    
    public String firstGameActions(HttpSession session, Quiz quiz,
            ArrayList<Tag> gameSelectedTags,
            HashSet<Question> selectedQuestions,
            HashMap<Tag,ArrayList<Question>> selectedTagsQuestions){
        
        gameSelectedTags = (ArrayList<Tag>) session.getAttribute("gameSelectedTags");
        selectedQuestions = new HashSet<Question>();
        selectedTagsQuestions = new HashMap<Tag,ArrayList<Question>>();
        
        ArrayList<Question> tagQuestions=null;
        
        if (gameSelectedTags != null) {
            for (Tag tag : gameSelectedTags) {
                tagQuestions=tags.questionsForTag(tag.getTagId());
                selectedQuestions.addAll(tagQuestions);
                selectedTagsQuestions.put(tag, tagQuestions);
            }
            if (selectedQuestions.isEmpty()) {
                session.setAttribute("emptySelection", true);
                return "/game_selection";
            } else {
                String language_code = (String) session.getAttribute("language");
                quiz = new Quiz(new ArrayList<Question>(selectedQuestions),selectedTagsQuestions, 0,language_code);
                quiz.askNextQuestion();
                
                session.setAttribute("quiz", quiz);
                session.setAttribute("emptySelection", false);
                session.setAttribute("currentQuizSize", quiz.getCurrentSize());
                System.out.println(quiz.getCurrentSize());
                return "/game";
            }
        } else {
            session.setAttribute("emptySelection", true);
            return "/game_selection";
        }
    }
    
    public String firstTrainingActions(HttpSession session, Training training,
            ArrayList<Tag> gameSelectedTags,
            HashSet<Question> selectedQuestions){
        
        gameSelectedTags = (ArrayList<Tag>) session.getAttribute("gameSelectedTags");
        selectedQuestions = new HashSet<Question>();
        
        ArrayList<Question> tagQuestions=null;
        
        if (gameSelectedTags != null) {
            for (Tag tag : gameSelectedTags) {
                tagQuestions=tags.questionsForTag(tag.getTagId());
                selectedQuestions.addAll(tagQuestions);
            }
            if (selectedQuestions.isEmpty()) {
                session.setAttribute("emptySelection", true);
                return "/game_selection";
            } else {
                String language_code = (String) session.getAttribute("language");
                training = new Training(new ArrayList<Question>(selectedQuestions),language_code);
                training.askNextQuestion();
                
                session.setAttribute("training", training);
                session.setAttribute("emptySelection", false);
                return "/training";
            }
        } else {
            session.setAttribute("emptySelection", true);
            return "/game_selection";
        }
    }
    
    public String allquestionsActions(HttpServletRequest request,
            HttpSession session,
            Tag selectedTag,
            Collection<Date> selectedDates,
            Collection<Period> selectedPeriods,
            Collection<ExactDate> selectedExactDates,
            Collection<Name> selectedNames,
            TagCategoriesWrapper tagCategoriesWrapper){
        
        String queryString = request.getQueryString();
        String language = (String) session.getAttribute("language");
        if (queryString != null) {
            Integer tagID=null;
            try{
                tagID = Integer.parseInt(queryString);
            }catch(NumberFormatException e){
                return "/notfound";
            }
            if (tagID != 0) {
                selectedTag = tags.find(tagID);
                if(selectedTag==null){
                    return "/notfound";
                }
                selectedDates = selectedTag.getDateCollection();
                selectedPeriods = selectedTag.getPeriodCollection();
                selectedExactDates = selectedTag.getExactDateCollection();
                selectedNames = selectedTag.getNameCollection();
            } else {
                selectedDates = dates.findAll();
                selectedPeriods = periods.findAll();
                selectedExactDates = exactDates.findAll();
                selectedNames = names.findAll();
            }
            setLanguage(language,selectedDates,selectedPeriods,selectedExactDates,selectedNames);
            request.setAttribute("selectedTag", selectedTag);
            request.setAttribute("selectedDates", selectedDates);
            request.setAttribute("selectedPeriods", selectedPeriods);
            request.setAttribute("selectedExactDates", selectedExactDates);
            request.setAttribute("selectedNames", selectedNames);
        }else{
            tagCategoriesWrapper = (TagCategoriesWrapper) session.getAttribute("tagCategoriesWrapper");
            if (tagCategoriesWrapper == null) {
                tagCategoriesWrapper = new TagCategoriesWrapper(tagsCategories);
                session.setAttribute("tagCategoriesWrapper", tagCategoriesWrapper);
            }
        }
        return "/allquestions";
    }
    
    
    public String rankingsActions(HttpServletRequest request,HttpSession session,
            Tag selectedTag,ArrayList<Score> selectedScores,TagCategoriesWrapper tagCategoriesWrapper){
        String queryString = request.getQueryString();
        
        Player player = getAndSetLoggedPlayer(request,session);
        
        if (queryString != null) {
            Integer tagID = Integer.parseInt(queryString);
            selectedTag = tags.find(tagID);
            request.setAttribute("selectedTag", selectedTag);
            for (Score s : selectedTag.getScoreCollection()) {
                selectedScores.add(s);
            }
            Collections.sort(selectedScores);
            Collections.reverse(selectedScores);
            request.setAttribute("selectedScores", selectedScores);
        }else{
            tagCategoriesWrapper = (TagCategoriesWrapper) session.getAttribute("tagCategoriesWrapper");
            if (tagCategoriesWrapper == null) {
                tagCategoriesWrapper = new TagCategoriesWrapper(tagsCategories);
                session.setAttribute("tagCategoriesWrapper", tagCategoriesWrapper);
            }
        }
        return "/rankings";
    }
    
    public String profileActions(HttpServletRequest request,HttpSession session,Collection<Score> playerScores){

        Player player = getAndSetLoggedPlayer(request, session);

        playerScores = player.getScoreCollection();

        session.setAttribute("loggedPlayerScores", playerScores);
        
        session.setAttribute("changePasswordRequired", false);

        return "/profile";

    }
    
    public String createAccountActions(HttpServletRequest request,HttpSession session){
        String pseudo = request.getParameter("newAccountPseudo");
        String password = request.getParameter("newAccountPassword");
        String passwordConfirm = request.getParameter("newAccountPasswordConfirm");
        String email = request.getParameter("newAccountEmail");

        boolean validationErrorFlag = false;
        validationErrorFlag = validator.validateNewAccountForm(pseudo, password, passwordConfirm, email, request);
        boolean usedPseudoErrorFlag = false;
        usedPseudoErrorFlag = validator.validateUniquePseudo(pseudo, registeredUsers);

        if (validationErrorFlag) {
            request.setAttribute("validationErrorFlag", validationErrorFlag);
            return "/login";
        } else if (usedPseudoErrorFlag) {
            request.setAttribute("usedPseudoErrorFlag", usedPseudoErrorFlag);
            return "/login";
        } else {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                String hashedPassword = bytesToHex(hash);
                
                Player player = npManager.addPlayer(pseudo, hashedPassword, email, groups.find(registeredUserGroupId));
                mailManager.sendWelcomeMail(email);
                session.setAttribute("player", player);
                registeredUsers = groups.find(registeredUserGroupId).getPlayerCollection();
                
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControllerActions.class.getName()).log(Level.SEVERE, null, ex);
                return "/error";
            }
            return "/account_created";
        }
    }
    
    public String gameEndActions(Quiz quiz,ArrayList<Tag> gameSelectedTags,
            Collection<Score> playerScores,
            HashSet<Question> selectedQuestions,
            HashMap<Tag,ArrayList<Question>> selectedTagsQuestions,
            HttpServletRequest request,HttpSession session){
        String queryString = request.getQueryString();
            if(queryString.equals("saveScore")){
                
                Player loggedPlayer = getAndSetLoggedPlayer(request,session);
                if(loggedPlayer!=null){
                    
                    quiz = (Quiz) session.getAttribute("quiz");
                    
                    nsManager.addNewScores(quiz.getTagsScores(),loggedPlayer, new java.util.Date());
                    
                    return profileActions(request,session,playerScores);
                }else{
                    return "/game_end";
                }
            }
            else if(queryString.equals("tryAgain")){
                
                return firstGameActions(session,quiz,gameSelectedTags,selectedQuestions,selectedTagsQuestions);
                
            }else{
                return null;
            }
    }
    
    public String addTagActions(HttpServletRequest request,HttpSession session,ArrayList<Tag> gameSelectedTags){
        String tagName = request.getParameter("tagSelection");
            
            if(gameSelectedTags==null){
                gameSelectedTags = new ArrayList<Tag>();
                session.setAttribute("gameSelectedTags", gameSelectedTags);
            }
            
            if(tagName.equals("Tous")){
                gameSelectedTags.clear();
                gameSelectedTags.addAll(tags.findAll());
            }else{
                Tag tagToAdd = tags.tagForName(tagName);
                if(tagToAdd!=null &&!gameSelectedTags.contains(tagToAdd)){
                    gameSelectedTags.add(tagToAdd);
                }
            }
            session.setAttribute("emptySelection", false);
            return "/game_selection";
    }
    
    public String removeTagActions(HttpServletRequest request,HttpSession session,ArrayList<Tag> gameSelectedTags){
        String tagName = request.getParameter("tagName");
            
            if(tagName!=null &&gameSelectedTags!=null){
                Tag tagToRemove = tags.tagForName(tagName);
                gameSelectedTags.remove(tagToRemove);
                session.setAttribute("gameSelectedTags", gameSelectedTags);
            }
            
            return "/game_selection";
    }
    
    public String passwordChangedActions(HttpServletRequest request,HttpSession session){
        Player loggedPlayer = getAndSetLoggedPlayer(request,session);
            
            String currentPasswordEntered = request.getParameter("currentPassword");
            String realPassword = loggedPlayer.getPassword();
            String newPassword = request.getParameter("newPassword");
            String confirmNewPassword = request.getParameter("confirmNewPassword");
            
            boolean validationErrorFlag = false;
            validationErrorFlag = validator.validateNewPassword(request, currentPasswordEntered ,realPassword , newPassword, confirmNewPassword);
            
            if (validationErrorFlag) {
                request.setAttribute("validationErrorFlag", validationErrorFlag);
                return "/profile";
            } else {
                npManager.changePassword(loggedPlayer, newPassword);
                return "/profile_saved";
            }
    }
    
    public String changePseudoEmailActions(HttpServletRequest request,HttpSession session){
        Player loggedPlayer = getAndSetLoggedPlayer(request,session);
            String currentPseudo = loggedPlayer.getPseudo();
            String currentEmail = loggedPlayer.getEmail();
            
            String newPseudo = request.getParameter("newPseudo");
            String newEmail = request.getParameter("newEmail");
            
            boolean pseudoErrorFlag = false;
            boolean pseudoTaken = false;
            boolean emailErrorFlag = false;
            
            if(!newPseudo.equals(currentPseudo)){
                pseudoErrorFlag = validator.validateNewPseudo(newPseudo);
                if(pseudoErrorFlag){
                    request.setAttribute("newPseudoError",true);
                }
                pseudoTaken = validator.validateUniquePseudo(newPseudo, registeredUsers);
                if(pseudoTaken){
                    request.setAttribute("newPseudoTaken", true);
                }
                if(!pseudoErrorFlag && !pseudoTaken){
                    npManager.changePseudo(loggedPlayer, newPseudo);
                    session.invalidate();
                }
            }
            if(!newEmail.equals(currentEmail)){
                emailErrorFlag = validator.validateNewEmail(newEmail);
                if(emailErrorFlag){
                    request.setAttribute("newEmailError",true);
                }else{
                    npManager.changeEmail(loggedPlayer, newEmail);
                }
            }
            
            if(pseudoErrorFlag||emailErrorFlag||pseudoTaken){
                return "/profile";
            }else{
                return "/profile_saved";
            }
    }
    
    public String sendContactMailActions(HttpServletRequest request,HttpSession session){
        String subject = request.getParameter("mailSubject");
            String message = request.getParameter("mailMessage");
            
            String from;
            Player loggedPlayer = getAndSetLoggedPlayer(request,session);
            if(loggedPlayer==null){
                from =MailManager.serverAddress;
            }else{
                from =loggedPlayer.getEmail();
            }
            String to = MailManager.serverAddress;
            
            boolean error=validator.validateSendMail(subject, message, request);
            
            if(error){
                return "/contact";
            }else{
                int status = mailManager.sendMail(from, to, message, subject);
                if(status==-1){
                    return "/contact_not_sent";
                }else{
                    return "/contact_sent";
                }
            }
    }
    
    public void setQuizLanguage(HttpServletRequest request,HttpSession session,String language){
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        if(quiz!=null){
            quiz.setLanguage(language);
        }
    }
    
    private Player getAndSetLoggedPlayer(HttpServletRequest request,HttpSession session){
        String loggedPseudo = request.getRemoteUser();
        registeredUsers = groups.find(registeredUserGroupId).getPlayerCollection();
        Player player = players.playerForPseudo(loggedPseudo, registeredUsers);
        session.setAttribute("loggedPlayer", player);
        return player;
    }
    
    private void setLanguage(String language, 
            Collection<Date> selectedDates,
            Collection<Period> selectedPeriods,
            Collection<ExactDate> selectedExactDates,
            Collection<Name> selectedNames){
        for(Date d: selectedDates){
            d.setLanguage(language);
        }
        for(Period p: selectedPeriods){
            p.setLanguage(language);
        }
        for(ExactDate ed: selectedExactDates){
            ed.setLanguage(language);
        }
        for(Name n: selectedNames){
            n.setLanguage(language);
        }
    }
    
    private final static char[] hexArray = "0123456789abcdef".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
