# Casos de uso

***Caso de uso No.1***
|Caso de uso| Ingresar al sistema |
|---|---|
|Actores| Usuario, base de datos. |
|Propósito| Este caso de uso se encarga de describir el proceso cuando un usuario accede al sistema. |
|Resumen| El usuario ingresa al sistema mediante sus credenciales asociados a su cuenta cliente. |
|Pre-condición| El usuario debe tener una cuenta cliente registrada en la base de datos. |

__Secuencia principal__
|Pasos| Secuencia |
|---|---|
|1| El sistema despliega la interfaz donde se pide las credenciales para acceder a una cuenta cliente. |
|2| El usuario ingresa sus credenciales. |
|3| Se hace una consulta a la base de datos para validar las credenciales ingresadas. |
|4| El sistema despliega el menú principal. |
|5| El usuario selecciona el botón "Salir". |
|Postcondición| Ninguna. | 

|Excepciones| |
|---|---|
|1| El usuario ingresa su número de cliente o contraseña incorrecta. El sistema le notifica que los datos ingresados son incorrectos. |
|2| El usuario ingresa diferentes tipos de datos a los solicitados por el sistema. El sistema le notifica que los tipos de datos son incorrectos. |
<br />
<br />



***Caso de uso No.2***
|Caso de uso| Consultar datos personales |
|---|---|
|Actores| Usuario, base de datos. |
|Propósito| Este caso de uso se encarga de describir el proceso cuando un usuario consulta información personal asociada a su cuenta cliente. |
|Resumen| El usuario solicita consultar sus datos personales y el sistema los despliega. |
|Pre-condición| El usuario debe de haber ingresado al sistema al sistema. |

__Secuencia principal__
|Pasos| Secuencia |
|---|---|
|1| El sistema despliega el menú principal. |
|2| El usuario selecciona la opción "Consultar información". |
|3| El sistema hace una consulta para recuperar los datos de la cuenta cliente. |
|4| El sistema despliega los datos personales del usuario. |
|5| El usario selecciona el botón "Cerrar" y regresa al menú principal. |
|Postcondición| Ninguna. | 

|Excepciones| |
|---|---|
|1| Ninguna. |
<br />
<br />



***Caso de uso No.3***
|Caso de uso| Modificar datos personales |
|---|---|
|Actores| Usuario, base de datos. |
|Propósito| Este caso de uso se encarga de describir el proceso cuando un usuario actualiza sus datos personales. |
|Resumen| El usuario desea modificar algún/algunos datos personales y lo realiza mediante el sistema. |
|Pre-condición| El usuario debe de haber ingresado al sistema al sistema. |

__Secuencia principal__
|Pasos| Secuencia |
|---|---|
|1| El sistema despliega el menú principal. |
|2| El usuario selecciona la opción "Modificar información". |
|3| El sistema despliega todos los campos que contienen los datos personales de la cuenta cliente. |
|4| El usuario modifica los datos que son permitidos por el sistema. |
|4.1| El usuario introduce su contraseña. |
|4.2| El usuario presiona el botón "Confirmar". |
|5| El sistema hace una consulta para modificar los datos personales del cliente. |
|6| El sistema muestra un mensaje de que la modificación se realizó exitosamente. |
|7| El usario selecciona el botón "Cerrar" y regresa al menú principal. |
|Postcondición| Se actualizan los datos de la cuenta cliente en la base de datos. | 

|Excepciones| |
|---|---|
|1| El usuario ingresa incorrectamente la constraseña. El sistema le notifica que es incorrecta. |
|2| El usuario ingresa en los campos solicitados diferentes tipos de datos. El sistema le notifica que los tipos de datos ingresados son incorrectos. |
|3| El usuario no sigue el formato de correo electrónico o excede la cantidad de caracteres permitidos. El sistema le notifica que debe seguir un formato. |
<br />
<br />


***Caso de uso No.4***
|Caso de uso| Realizar una transferencia bancaria |
|---|---|
|Actores| Usuario, base de datos. |
|Propósito| Este caso de uso se encarga de describir el proceso cuando un cliente realiza una transferencia con alguna de sus cuentas bancarias. |
|Resumen|  |
|Pre-condición| Ninguna |

__Secuencia principal__
|Pasos| Secuencia |
|---|---|
|1| El sistema despliega el menú principal. |
|2| El usuario selecciona la opción "Acceder cuenta bancaria". |
|3| El sistema despliega un menú donde el usuario escogerá en cuál cuanta desea realizar la transferencia. |
|4| El usuario selecciona alguna de sus cuentas bancarias. |
|4.1| El usuario selecciona el botón "Realizar transferencia". |
|5| El sistema despliega una interfaz gráfica donde se muestran los campos necesarios para realizar la transferencia. |
|6| El usuario llena los campos solicitados. |
|6.1| El usuario selecciona el botón "Confirmar". |
|7| El sistema realiza la transferencia y la registra en la base de datos. |
|8| El sistema le notifica al usuario que la transferencia se realizó exitosamente. |
|Postcondición| Se agrega una nueva cuenta cliente a la base de datos | 

|Excepciones| |
|---|---|
|1| El usuario ingresa incorrectamente los campos solicitados. El sistema le notifica que los datos ingresados son incorrectos|
|2| La cuenta cliente del usuario no tiene ninguna cuenta bancaria. El sistema le notifica que los datos ingresados son incorrectos|
|3| El saldo no es suficiente. El sistema le notifica que el saldo no es suficiente para realizar la transferencia. |
<br />
<br />


***Caso de uso No.5***
|Caso de uso| Consultar estado de cuenta |
|---|---|
|Actores| Usuario, base de datos. |
|Propósito| Este caso de uso se encarga de describir el proceso cuando un gerente de banco registra una nueva cuenta cliente. |
|Resumen| El gerente de banco entra al sistema y agrega una nueva cuenta cliente a la base de datos. |
|Pre-condición| Ninguna |

__Secuencia principal__
|Pasos| Secuencia |
|---|---|
|1| El sistema despliega el menú principal. |
|2| El usuario selecciona la opción "Acceder cuenta bancaria". |
|3| El sistema despliega un menú donde el usuario escogerá en cuál cuanta desea realizar la transferencia. |
|4| El usuario selecciona alguna de sus cuentas bancarias. |
|4.1| El usuario selecciona el botón "Consultar Estado de Cuenta". |
|5| El sistema hace una consulta a la base de datos para recupearar todas las transacciones en la que está involucrada la cuenta bancaria seleccionada. |
|6| Se despliegan las transaciones realizadas en una tabla. |
|7| El usuario sale del sistema. |
|Postcondición| Se agrega una nueva cuenta cliente a la base de datos | 

|Excepciones| |
|---|---|
|1| Ninguna. |
<br />
<br />
