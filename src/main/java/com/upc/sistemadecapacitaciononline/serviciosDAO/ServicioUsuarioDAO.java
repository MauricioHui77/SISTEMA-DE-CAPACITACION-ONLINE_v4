package com.upc.sistemadecapacitaciononline.serviciosDAO;

import com.upc.sistemadecapacitaciononline.entidades.Rol;
import com.upc.sistemadecapacitaciononline.entidades.Usuario;
import com.upc.sistemadecapacitaciononline.repositorio.RolRepositorio;
import com.upc.sistemadecapacitaciononline.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

@Service
public class ServicioUsuarioDAO {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;

    public Usuario registrar(String nombre, Usuario usuario) throws NoSuchAlgorithmException {
        Usuario u;
        String usuarioPass = "";
        String passHashed = "";
        byte[] salt = getSalt();
        try {
            Rol rol= rolRepositorio.buscarRolPorNombre(nombre);
            usuario.setIdRol(rol);
            usuarioPass = usuario.getPassword();
            passHashed = obtenerPasswordHASH(salt, usuarioPass);
            usuario.setPassword(passHashed);
            usuario.setSalt(salt);
            u = usuarioRepositorio.save(usuario);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear el usuario", e);
        }
        return u;
    }

    public Usuario usuarioTieneAccesos (String username, String password) {
        Usuario u;
        String passwordHashed = "";
        boolean tieneAccesos = false;
        try {
            u = usuarioRepositorio.buscarUsuarioPorCorreo(username);

            if (u != null) {
                passwordHashed = obtenerPasswordHASH(u.getSalt(), password);

                if (u.getPassword().equals(passwordHashed)){
                    tieneAccesos = true;
                }
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar al usuario", e);
        }
        return u;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }

    public String obtenerPasswordHASH(byte[] salt, String password) throws NoSuchAlgorithmException {
        String passwordToHash = password;


        String securePassword = obtenerSHA256(passwordToHash, salt);
        System.out.println(securePassword);

        return securePassword;
    }

    public String obtenerSHA256 (String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public List<Usuario> obtenerUsuariosPorRol (Long idRol) {
        List<Usuario> usuarios = null;

        try {
            usuarios = usuarioRepositorio.buscarUsuariosPorRol(idRol);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron usuarios", e);
        }
        return usuarios;
    }
}
