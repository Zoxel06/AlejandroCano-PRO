import dto.UsuarioDTO;
import model.Usuario;

import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Connection connection = DBConnector.getConnection();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = new Usuario(1, "Pedri", "Potter", "pedri@gmail", "balondeoro"
                , 0, 0);

        //usuarioDTO.insertarUsuarios(usuario);

        //usuarioDTO.listarUsuarios();
        //System.out.println("el numero de despidos es de "+usuarioDTO.borrarUsuario(55500));
        // usuarioDTO.insertarUsuarios(usuario);
        /*Scanner scanner = new Scanner(System.in);
        boolean fallo = false;
        do {
            try {
                usuarioDTO.insertarUsuariosPS(usuario);
                fallo = false;
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Por favor introduce un correo diferente");
                String correo = scanner.next();
                usuario.setCorreo(correo);
                fallo = true;
            } catch (SQLException e){
                System.out.println("Error en la query");
            }
        } while (fallo);*/

        // hacer un sistema que permite el login de los usuarios. Para ello
        // el sistema pedirá el correo y pass del usuario que quiere acceder
        // al sistema. En caso de hacer un login correcto,
        // aparecera el mensaje de bienvenido a la app
        // en caso de no hacer login, aparecerá el mensaje de
        // fallo de seguridad


        int idLogin = usuarioDTO.logearUsuario("zoxel14@gmail.com", "1234");
        if (idLogin != -1) {
            System.out.println("Entrando a la app...");
            System.out.println("Entrando a la app en modo " + idLogin);
        } else {
            System.out.println("Error en el login");
        }

    }
}