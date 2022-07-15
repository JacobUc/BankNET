## MVC en la implementación del sistema


***Caso de uso No.1***
|Caso de uso| Ingresar al sistema |
|---|---|
|Model| Se usaron las clases CuentaCliente, DBCuentaClienteQuery. |
|View| Se usó la clase LoginUI. |
|Controller| Se usaron las clases LoginUIController, CuentaClienteController. |
|Resumen| Primero se hace visible el jFrame de la clase LoginUI, esta clase se encarga de capturar los datos ingresados por el usuario. La clase LoginController se encarga de validarlos usando métodos de la clase CuentaClienteController, la clase LoginController también usa un método de CuentaClienteController que a su vez usa un método de la clase DBCuentaClienteQuery para realizar una consulta a la base de datos. El resultado de esta consulta es procesada en un método de la clase CuentaClienteController, el cual es usado por la clase LoginController para permitir el acceso del sistema al usuario. |
<br />
<br />


***Caso de uso No.2***
|Caso de uso| Consultar datos personales |
|---|---|
|Model| Se usaron las clases CuentaCliente, DBCuentaClienteQuery. |
|View| Se usaron las clases ConsultarInformacionClienteUI, MenuCuentaClienteUI. |
|Controller| Se usaron las clases ConsultarInformacionClienteController, MenuCuentaClienteController, CuentaClienteController. |
|Resumen| Desde la clase MenuCuentaClienteController se invoca la clase ConsultarInformacionClienteController cuando el usuario presiona el botón "Consultar informacion". Se hace visible el jFrame de la clase ConsultarInformacionClienteUI, donde la clase ConsultarInformacionClienteController agrega los datos del cliente usando métodos de la clase CuentaClienteController, quien a su vez usa a la clase DBCuentaClienteQuery para realizar las consultas a la base de datos. |
<br />
<br />



***Caso de uso No.3***
|Caso de uso| Modificar datos personales |
|---|---|
|Model| Se usaron las clases CuentaCliente, DBCuentaClienteQuery. |
|View| Se usaron las clases ActualizarDatosUI, MenuCuentaClienteUI. |
|Controller| Se usaron las clases ActualizarDatosClienteController, MenuCuentaClienteController, CuentaClienteController. |
|Resumen| Desde la clase MenuCuentaClienteController se invoca la clase ActualizarDatosController cuando el usuario presiona el botón "Actualizar datos". Se hace visible el jFrame de la clase ActualizarDatosUI, donde ActualizarDatosClienteController agrega los datos del cliente con ayuda de los métodos de la clase CuentaClienteController. La clase ActualizarDatosUI es la encargada de recibir los datos ingresados por el usuario y se los proporciona a la clase ActualizarDatosClienteController para que valide. Esta última clase usa métodos de la clase CuentaClienteController, que a su vez usa métodos de la clase DBCuentaClienteQuery para realizar consultas y actualizaciones en la base de datos. |
<br />
<br />



***Caso de uso No.4***
|Caso de uso| Realizar una transferencia bancaria |
|---|---|
|Model| Se usaron las clases CuentaCliente, CuentaBancaria, Transaccion, DBCuentaClienteQuery, DBCuentaBancariaQuery, DBTransaccionQuery. |
|View| Se usaron las clases RealizarTransaccionUI, AccederCuentaBancariaUI, MenuCuentaClienteUI. |
|Controller| Se usaron las clases RealizarTransaccionController, AccederCuentaBancariaController, MenuCuentaClienteController, TransaccionController.  |
|Resumen| Desde la clase MenuCuentaClienteUI se invoca a la clase AccederCuentaBancariaController cuando el usuario presiona el botón "Acceder a una cuenta bancaria". Se hace visible el jFrame de la clase AccederCuentaBancariaUI, desde esta clase se invoca a la clase RealizarTransaccionController cuando el usuario presiona el botón "Realizar Transferencia". Se hace visible el jFrame de la clase RealizarTransaccionUI.Desde RealizarTransaccionController se usan métodos de la clase TransaccionController, la cual a su vez usa métodos de las clases DBTransaccionQuery, DBCuentaBancariaQuery para realizar consultas/actualizaciones en la base de datos y la clase CuentaBancariaController para ajustar los saldos de las cuentas.  |
<br />
<br />



***Caso de uso No.5***
|Caso de uso| Consultar estado de cuenta |
|---|---|
|Model| Se usaron las clases Transaccion, CuentaBancaria, DBTransaccionQuery, DBCuentaBancariaQuery.  |
|View| Se usaron las clases ConsultarEstadoCuentaUI, AccederCuentaBancariaUI, MenuCuentaClienteUI.  |
|Controller| Se usaron las clases ConsultarEstadoCuentaController, AccederCuentaBancariaController, MenuCuentaClienteController, CuentaBancariaController, TransaccionController.  |
|Resumen| Desde la clase MenuCuentaClienteUI se invoca a la clase AccederCuentaBancariaController cuando el usuario presiona el botón "Acceder a una cuenta bancaria". Se hace visible el jFrame de la clase AccederCuentaBancariaUI, desde esta clase se invoca a la clase ConsultarEstadoCuentaController cuando el usuario presiona el botón "Consultar Estado de Cuenta". Se hace visible el jFrame de la clase ConsultarEstadoCuentaUI. Desde ConsultarEstadoCuentaController se usan métodos de la clase TransaccionController, la cual a su vez usa métodos de las clases DBTransaccionQuery, DBCuentaBancariaQuery para realizar consultas en la base de datos. |
<br />
<br />

