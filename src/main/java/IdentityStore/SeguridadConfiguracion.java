package IdentityStore;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@BasicAuthenticationMechanismDefinition(realmName = "ApplicationRealm")

@DeclareRoles({"mobil"})

@ApplicationScoped
public class SeguridadConfiguracion {

}

