package IdentityStore;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import java.util.Set;

@ApplicationScoped
//@Vetoed
//Observar que en este proyecto existen dos implmenetaciones de IdentiyStore:
//ésta y la ValidadorDeCredenciales de memoria. El CDI no va a saber que implementación
//inyectar, por lo tanto lanzaría un error.
//Con Vetoed le decimos al CDI que ignore este bean.
public class ValidadorCredencialesDB implements IdentityStore{
	@PersistenceContext
	private EntityManager em;

	@Override
	public CredentialValidationResult validate(Credential credential) {
		System.out.println("** IdentityStore en base de datos");
		CredentialValidationResult resultado = CredentialValidationResult.INVALID_RESULT;
		
		UsernamePasswordCredential credencial = (UsernamePasswordCredential)credential;
		String usr = credencial.getCaller();
		String pass = credencial.getPasswordAsString();
		System.out.println("LA CONTRA ES:" + pass);
		Cliente usuario = findUsuario(usr);
		if (usuario != null) {
			System.out.println("encontre usuario: " + usuario.getNombreCompleto());
			if (usuario.contraCorrecta(pass)) {
				System.out.println("contraseña correcta");
				//al proceso de autorización le interasa saber los grupos a los que pertenece el usuari
				resultado =  new CredentialValidationResult
						(usr, Set.of("mobil"));
				
			} else {
				System.out.println("password incorrecta");
			}
		} else {
			System.out.println("No existe usuario.");
		}
		System.out.println("Retornando usuario: " + usr);
		System.out.println("Roles: " + Set.of("mobil"));
		System.out.println("RESULTADOOO:" + resultado.getCallerPrincipal());
		System.out.println("RESULTADOOO:" + resultado.getCallerGroups());


		return resultado;
	
	}

	private Cliente findUsuario(String cedula) {
		try {
			return em.createQuery(
							"SELECT c FROM Cliente c WHERE c.cedula = :cedula",
							Cliente.class)
					.setParameter("cedula", cedula)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
