import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EnviarCorreo {

    public static void main(String[] args) {

        String correoEmisor = "tucorreo@dominio.com";
        String contraseña = "ejmcontraseña: pltw sqgv wlhq wkpe";

        String correoReceptor = "correoquerecibe@dominio.com";

        Properties propiedades = new Properties();

        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");

        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEmisor, contraseña);
            }
        });

        try {

            Message mensaje = new MimeMessage(sesion);

            mensaje.setFrom(new InternetAddress(correoEmisor));

            mensaje.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(correoReceptor)
            );

            mensaje.setSubject("Correo enviado desde Java");

            mensaje.setText("Integrante del equipo:\n\nSergio Quemba");

            Transport.send(mensaje);

            System.out.println("Correo enviado correctamente");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}