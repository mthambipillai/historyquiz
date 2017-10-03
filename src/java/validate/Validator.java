
package validate;

import entity.Player;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

public class Validator {
    private final int pseudoMaxLength=45;
    private final int passwordMaxLength=256;
    private final int emailMaxLength=95;
    private final int subjectMaxLength=30;
    private final int messageMaxLength=500;
    private final char[] forbiddenChars = {'<','>','\"','\'',';'};

    public boolean validateNewAccountForm(String pseudo,
                                String password,
                                String confirmPassword,
                                String email,
                                HttpServletRequest request) {

        boolean errorFlag = false;
        boolean pseudoError;
        boolean passwordError;
        boolean confirmPasswordError;
        boolean emailError;

        if (pseudo == null
                || !sanitizedInput(pseudo)
                || pseudo.equals("")
                || pseudo.length() > pseudoMaxLength) {
            errorFlag = true;
            pseudoError = true;
            request.setAttribute("pseudoError", pseudoError);
        }
        if (password == null
                || !sanitizedInput(password)
                || password.equals("")
                || password.length() > passwordMaxLength) {
            errorFlag = true;
            passwordError = true;
            request.setAttribute("passwordError", passwordError);
        }
        if (confirmPassword == null
                || !confirmPassword.equals(password) ){
            errorFlag = true;
            confirmPasswordError = true;
            request.setAttribute("confirmPasswordError", confirmPasswordError);
        }
        if (email == null
                || !sanitizedInput(email)
                || email.equals("")
                || !email.contains("@")
                || email.length() > emailMaxLength) {
            errorFlag = true;
            emailError = true;
            request.setAttribute("emailError", emailError);
        }

        return errorFlag;
    }
    
    public boolean validateUniquePseudo(String pseudo, Collection<Player> players){
        for(Player p : players){
            if(p.getPseudo().equals(pseudo)){
                return true;
            }
        }
        return false;
    }
    
    public boolean validateNewPassword(HttpServletRequest request,
                                        String currentPasswordEntered,
                                        String realCurrentPassword,
                                        String newPassword,
                                        String confirmNewPassword){
        
        boolean currentPasswordError;
        boolean newPasswordError;
        boolean confirmNewPasswordError;
        boolean errorFlag = false;
        
        if(!currentPasswordEntered.equals(realCurrentPassword)){
            errorFlag = true;
            currentPasswordError = true;
            request.setAttribute("currentPasswordError", currentPasswordError);
        }
        if (newPassword == null
                || !sanitizedInput(newPassword)
                || newPassword.equals("")
                || newPassword.length() > passwordMaxLength) {
            errorFlag = true;
            newPasswordError = true;
            request.setAttribute("newPasswordError", newPasswordError);
        }
        if (confirmNewPassword == null
                || !confirmNewPassword.equals(newPassword) ){
            errorFlag = true;
            confirmNewPasswordError = true;
            request.setAttribute("confirmNewPasswordError", confirmNewPasswordError);
        }
        
        return errorFlag;
    }
    
    public boolean validateNewPseudo(String newPseudo){
        boolean errorFlag = false;
        if (newPseudo == null
                || !sanitizedInput(newPseudo)
                || newPseudo.equals("")
                || newPseudo.length() > pseudoMaxLength) {
            errorFlag = true;
        }
        return errorFlag;
    }
    
    public boolean validateNewEmail(String newEmail){
        boolean errorFlag = false;
        if (newEmail == null
                || !sanitizedInput(newEmail)
                || newEmail.equals("")
                || !newEmail.contains("@")
                || newEmail.length() > emailMaxLength) {
            errorFlag = true;
        }
        return errorFlag;
    }
    public boolean validateSendMail(String subject,String message,HttpServletRequest request){
        boolean errorFlag = false;
        if(subject == null
                || subject.equals("")
                || subject.length() > subjectMaxLength){
            errorFlag = true;
            request.setAttribute("mailSubjectError", true);
        }
        if(message == null
                || message.equals("")
                || message.length() > messageMaxLength){
            errorFlag = true;
            request.setAttribute("mailMessageError", true);
        }
        return errorFlag;
    }
    
    
    private boolean sanitizedInput(String input){
        char f;
        for(int i=0;i<forbiddenChars.length;i++){
            f = forbiddenChars[i];
            if(input.indexOf(f)!=-1){
                return false;
            }
        }
        return true;
    }
}