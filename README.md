# Documentacion

## Login y Usuarios
    
   Al ejecutarse la aplicacion por primera vez, el usuario es enviado al login, dicho login permite el
   acceso solo a algunos usuarios que ya han sido definidos, el siguiente apartado lista dichos 
   usuarios y donde cambiarlos en caso de necesitarlo.
     
   - ### Usuarios predefinidos

        Usuario | Contraseña
        :---: | :---:
        user | 123456
        admin | 123456
        jesualex | 123456

   - ### Crear o cambiar usuarios
        los usuarios que el login permite actualmente se encuentran
        definidos en la funcion
        [crearUsuariosPrueba](app/src/main/java/com/jesualex/pruebaAgence/SQLite/Utils/UsuariosUtils.java#L105-135)
        como una lista de objetos
        [Usuario](app/src/main/java/com/jesualex/pruebaAgence/Utils/Usuario.java)
        dichos objetos se crean enviando al constructor:

        Tipo | Parametro
        :---: | :---:
        String | seudonimo
        String | email
        String | pass
        String | nombres
        String | apellidos
