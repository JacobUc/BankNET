## MVC en la implementación del sistema


***Caso de uso No.1***
|Caso de uso| Ingresar al sistema |
|---|---|
|Model| Se usaron las clases CuentaCliente, DBCuentaClienteQuery. |
|View| Se usó la clase LoginUI. |
|Controller| Se usaron las clases LoginUIController, CuentaClienteController. |
|Resumen| Primero se hace visible el jFrame de la clase LoginUI, esta clase se encarga de capturar los datos ingresados por el usuario. La clase LoginController se encarga de validarlos usando métodos de la clase CuentaClienteController, esta primer clase también usa un método de CuentaClienteController que a su vez usa un método de la clase DBCuentaClienteQuery para realizar una consulta a la base de datos. El resultado de esta Consulta es procesada un método de la clase CuentaClienteController, y este método es usado por la clase LoginController para permitir el acceso del sistema al usuario. |
<br />
<br />


***Caso de uso No.2***
|Caso de uso| Consultar datos personales |
|---|---|
|Model| Se usaron las clases CuentaCliente, DBCuentaClienteQuery. |
|View| Se usaron las clases ConsultarInformacionClienteUI, MenuCuentaClienteUI. |
|Controller| Se usaron las clases ConsultarInformacionClienteController, MenuCuentaClienteController. |
|Resumen| Desde la clase MenuCuentaClienteController se invoca a la clase ConsultarInformacionClienteController cuando el usuario presiona el botón "Consultar Informacion". Se hace visible el jFrame de la clase ConsultarInformacionClienteUI, donde ConsultarInformacionClienteController agrega los datos del cliente usando métodos de la clase CuentaClienteController, quien a su vez usa a la clase DBCuentaClienteQuery para realizar las consultas a la base de datos.  |
<br />
<br />



***Caso de uso No.3***
|Caso de uso| Modificar datos personales |
|---|---|
|Model| Se usaron las clases CuentaCliente, DBCuentaClienteQuery. |
|View| Se usaron las clases ActualizarDatosUI, MenuCuentaClienteUI. |
|Controller| Se usaron las clases ActualizarDatosClienteController, MenuCuentaClienteController. |
|Resumen| Desde la clase MenuCuentaClienteController se invoca a la clase Actualiza cuando el usuario presiona el botón "Actualizar datos". Se hace visible el jFrame de la clase ActualizarDatosUI, donde ActualizarDatosClienteController agrega los datos del cliente usando métodos de la clase CuentaClienteController. La clase ActualizarDatosUI es la encargada de recibir los datos ingresados por el usuario y se los proporciona a la clase ActualizarDatosClienteController para que valide. Esta última clase usa métodos de la clase CuentaClienteController, que a su vez usa métodos de la clase DBCuentaClienteQuery para realizar consultas y actualizaciones en la base de datos. |
<br />
<br />



***Caso de uso No.4***
|Caso de uso| Realizar una transferencia bancaria |
|---|---|
|Model| Se usaron las clases CuentaCliente, CuentaBancaria, Transaccion, DBCuentaClienteQuery, DBCuentaBancariaQuery, DBTransaccionQuery. |
|View| Se usaron las clases RealizarTransaccionUI, AccederCuentaBancariaUI, MenuCuentaClienteUI. |
|Controller| Se usaron las clases RealizarTransaccionController  |
|Resumen| Desde la clase MenuCuentaClienteUI se invoca a la clase ConsultarInformacionClienteController cuando el usuario presiona el botón "Realizar Transferencia". Desde la clase AccederCuentaBancariaUI se invoca a la clase RealizarTransaccionController cuando el usuario presiona el botón "Consultar Transferencias". Desde RealizarTransaccionController se usan métodos de las clases TransaccionController, la cual a su vez usa métodos de las clases DBTransaccionQuery, DBCuentaBancariaQuery para realizar consultas/actualizaciones en la base de datos y la clase CuentaBancariaController para recuperar datos de la cuenta bancaria como el saldo.  |
<br />
<br />



***Caso de uso No.5***
|Caso de uso| Consultar estado de cuenta |
|---|---|
|Model| Se usaron las clases Transaccion, CuentaBancaria, DBTransaccionQuery, DBCuentaBancariaQuery.  |
|View| Se usaron las clases ConsultarEstadoCuentaUI, AccederCuentaBancariaUI, MenuCuentaClienteUI.  |
|Controller| Se usaron las clases ConsultarEstadoCuentaController, AccederCuentaBancariaController, MenuCuentaClienteController, CuentaBancariaController, TransaccionController.  |
|Resumen| Desde la clase MenuCuentaClienteUI se invocan a las clases ConsultarInformacionClienteController y ConsultarInformacionClienteUI cuando el usuario presiona el botón "Acceder Cuenta Bancaria". Desde la clase AccederCuentaBancariaUI se invocan a la clases ConsultarEstadoCuentaController y ConsultarEstadoCuentaUI cuando el usuario presiona el botón "Consultar Transferencias". Desde ConsultarEstadoCuentaController se usan métodos de la clase TransaccionController, la cual a su vez usa métodos de las clases DBTransaccionQuery, DBCuentaClienteQuery para procesar las consultas que se realizan a la base de datos y la clase CuentaBancariaController para recuperar datos de la cuenta bancaria. |
<br />
<br />

