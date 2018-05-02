package ar.com.flexibility.examen.utils;

import static org.junit.Assert.assertArrayEquals;

import org.apache.tomcat.util.codec.binary.Base64;

import ar.com.flexibility.examen.exception.ClientException;

public class Authentication {
	
    private String user;
    private String password;
    
   
    private String getUser()
    {
        return user;
    }

    private void setUser(String user)
    {
        this.user = user;
    }

    private String getPassword()
    {
        return password;
    }


    private void setPassword(String password)
    {
        this.password = password;
    }

    private Authentication()
    {
        super();
    }

    private Authentication(String user, String password)
    {
        super();
        this.user = user;
        this.password = password;
    }
    
    private Authentication(String credential64 )
    {
        super();
        
        credential64 = credential64.replaceAll("Basic ", "");
        
        String decoded = new String(Base64.decodeBase64(credential64));
        
        String user = decoded.substring(0, decoded.indexOf(":"));
        
        String password = decoded.substring(decoded.indexOf(":") + 1);
        
        this.user = user;
        
        this.password = password;
    }
    
    public static void checkCredentials(String credentials) throws CredentialException {
        String user = "fede";
        String password = "1q2w3e4r";
        Authentication credentialsObj = new Authentication(credentials);
        if(!(credentialsObj.getUser().equals(user) && credentialsObj.getPassword().equals(password))) 
        {
            throw new CredentialException(CredentialException.E001, "User or password are invalid.");
        }

        
	}
}
