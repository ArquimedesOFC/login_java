import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class TwoFactorAuthSystem {
    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, String> authCodes = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cadastro 
        System.out.print("Cadastre um usuário: ");
        String username = scanner.nextLine();
        System.out.print("Cadastre uma senha: ");
        String password = scanner.nextLine();
        users.put(username, password);
        System.out.println("Usuário cadastrado com sucesso!\n");

        // Login dois fatores
        System.out.print("Usuário: ");
        String inputUser = scanner.nextLine();
        System.out.print("Senha: ");
        String inputPass = scanner.nextLine();

        if (authenticate(inputUser, inputPass)) {
            String authCode = generateAuthCode(inputUser);
            System.out.println("Seu código de autenticação é: " + authCode);
            
            System.out.print("Digite o código recebido: ");
            String inputCode = scanner.nextLine();
            
            if (verifyAuthCode(inputUser, inputCode)) {
                System.out.println("Login bem-sucedido! Acesso concedido. 🚀");
            } else {
                System.out.println("Código inválido! Acesso negado.");
            }
        } else {
            System.out.println("Usuário ou senha incorretos!");
        }
        
        scanner.close();
    }

    private static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private static String generateAuthCode(String username) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        authCodes.put(username, code);
        return code;
    }

    private static boolean verifyAuthCode(String username, String code) {
        return authCodes.containsKey(username) && authCodes.get(username).equals(code);
    }
}