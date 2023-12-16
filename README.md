Android App
A continuación se lista el uso de tecnologías para la creación de la aplicación móvil Android:

Lenguaje:
Koltin como lenguaje principal de desarrollo Android
XML para la creación de vistas

Arquitectura:
La arquitectura utilizada para el desarrollo de la aplicación Android es la arquitectura recomendada por Google MVVM + Repository: se utiliza para separar las preocupaciones en una aplicación Android. Se compone de tres componentes principales:
Modelo (Model): Representa los datos y la lógica empresarial de la aplicación.
Vista (View): Representa la interfaz de usuario de la aplicación, como las actividades o fragmentos de Android.
ViewModel: Actúa como un intermediario entre el Modelo y la Vista. Su objetivo principal es proporcionar los datos necesarios para la interfaz de usuario y manejar la lógica de presentación. El ViewModel se comunica con el Modelo para obtener y actualizar datos, y la Vista observa los cambios en el ViewModel para reflejarlos en la interfaz de usuario. LiveData se utiliza para facilitar esta comunicación.
Repositorio (Repository): El patrón de Repositorio se utiliza para abstraer la fuente de datos subyacente (como una base de datos local y un servicio web) de la capa de ViewModel. El Repositorio actúa como una única fuente de verdad para los datos y proporciona una interfaz clara para que el ViewModel acceda a ellos. Esto facilita la gestión de la lógica de recuperación y almacenamiento de datos en un solo lugar, lo que hace que la aplicación sea más mantenible y extensible.

Librerías utilizadas:

Retrofit: simplifica la comunicación entre una aplicación Android y un servidor web o una API REST al proporcionar una forma elegante y eficiente de realizar solicitudes HTTP y manejar las respuestas.
Hilt: es una biblioteca de inyección de dependencias (Dependency Injection, DI) desarrollada por Google para simplificar y mejorar la inyección de dependencias en aplicaciones Android. La inyección de dependencias es una técnica de diseño que permite gestionar las dependencias de los componentes de una aplicación de manera más eficiente y modular.
Android Navigation: simplifica la navegación dentro de las aplicaciones Android y ayuda a implementar patrones de navegación coherentes y robustos. Fue diseñado para facilitar la navegación entre destinos (como fragmentos o actividades) en una aplicación de manera más estructurada y declarativa.
