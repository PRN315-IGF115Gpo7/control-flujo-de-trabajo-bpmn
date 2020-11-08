package com.gpo7.proceso.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpo7.proceso.entity.Mail;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.entity.password.PasswordForgotDto;
import com.gpo7.proceso.entity.password.PasswordResetToken;
import com.gpo7.proceso.repository.PasswordResetTokenRepository;
import com.gpo7.proceso.servicio.Impl.EmailServiceImpl;
import com.gpo7.proceso.servicio.Impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private PasswordResetTokenRepository tokenRepository;
	
	@Autowired
	@Qualifier("emailServiceImpl")
	private EmailServiceImpl emailServiceImpl;
	
	private static final String FORGOT_VIEW="password/forgot-password";

	@ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }
	@GetMapping
    public String displayForgotPasswordPage() {
		return FORGOT_VIEW;
    }
	@PostMapping
    public String processForgotPasswordForm(
    		@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
            BindingResult result,
            HttpServletRequest request) {
		if (result.hasErrors()){			
            return FORGOT_VIEW;
        }
        Usuario usuario = usuarioServiceImpl.findByEmail(form.getEmail());
        if (usuario == null){
            result.rejectValue("email", null, "No se encontró ninguna cuenta relacionado con esta dirección de correo electrónico.");
            return FORGOT_VIEW;
        }

        PasswordResetToken token = new PasswordResetToken();
        //UUID Genera una cadena de 128bits(16bytes)
        token.setToken(UUID.randomUUID().toString());
        token.setUsuario(usuario);
        //30 minutos de Expiracion
        token.setExpiryDate(30);
        tokenRepository.save(token);
        
        Mail mail = new Mail();
        mail.setFrom("WorkFlow <sigen.fia.eisi@gmail.com>");
        mail.setTo(usuario.getEmail());
        mail.setSubject("Restablecer Contraseña");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailServiceImpl.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }
}