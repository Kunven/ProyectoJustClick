# ProyectoJustClick
Proyecto Java Service Para JustClick.Media de Luis Andrade

Configuracion de la Base de Datos
1. El proyecto utiliza una base de datos de Postgres configurada para un servidor llamado "miniproyecto"
2. la url del servidor es localhost:5432
3. El usuario configurado en el proyecto es "postgres"
4. La contraseña es "123"
5. Estas credenciales estan quemadas asi que es importante usarlas exactamente igual o modificar el archivo DataMan.java en src.main.java.com.example.demo, en la linea 11.
6. Dentro del servidor se creo una base de datos llamada "miniproyecto"
7. Dentro de la base de datos se creo un esquema llamado "miniproyecto"
8. Dentro de este esquema esta creada la tabla a acceder, el query de creacion de la tabla esta en un archivo llamado create.sql en la raiz del proyecto
9. En la raiz del proyecto hay un archivo llamado insert.sql que es para llenar la tabla con datos de prueba para el proyecto.
10. Es importante que creen la base de datos como se utilizo para evitar errores en la ejecucion del proyecto.

Creacion de la instancia de elasticsearch
1. Como se pidio se levanto una instancia de elasticsearch para su uso en Docker
2. el proyecto esta configurado para que se conecte a una instancia en "localhost:9200"
3. La instancia se creo en docker asi que se puede crear facilmente ejecutando este comando desde el CMD con Docker instalado 
    docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.12.1

Funcionamiento del servicio
1. El servico tiene una interfaz grafica que consiste en un input de tipo texto y un boton buscar que hace el submit
2. Cuando el usuario ingresa texto y presiona buscar se busca en la base de datos si el registro existe, en caso de que exista se retorna el registro y se incrementa un contado de acceso en la base de datos
3. Si el registro no existe el servicio retorna un mensaje de error
4. Si el registro tiene un contado igual o mayor al numero de veces permitido el servicio retorna un mensaje de error de que se a pasado del limite de consultas
5. EL servicio retorna el contador y el valor maximo para que el usuario pueda ver cuantos intentos le queda

Funcionamiento del elasticsearch
1. Cuando se hace la consulta y el retorno se crean cookies con la informacion del usuario (sistema operativo, version del os, navegador, version del navegador), junto con el parametro enviado se encoda en una clase llamada Datos.java y se convierte en JSON
2. Al momento de que el servicio retorna la info del usuario sea exitosa o fallida se manda el JSON a la instancia de elasticsearch hosteada en localhost:9200
3. Si no existe indice se crea uno
4. Se van almacenando la informacion que se pidio en la instancia de elasticsearch despues de cada consulta, exitosa o fallida.
5. El nombre del indice de elasticsearch creado es "datos"
6. En caso de no saber se puede acceder a los registros ingresados con esta url "http://localhost:9200/datos/_search?pretty=true"




