В папке с проектом есть дамп базы. База должна называться aikam.
Название базы,логин,пароль можно поменять в классе App.
String url = "jdbc:postgresql://localhost:5432/aikam";
 String login = "postgres";
 String password = "vados";
 
 Приложение должно получать три аргумента:
 1) тип запроса ("search" или "stat")
 2) путь к файлу с json, который содержит необходимые для работы данные
 3) путь к файлу, в который будет записан результат работы
 Например: search input.json output.json
 Аргументы легче всего менять в Edit configuration в строке Program arguents.
 
 В папке с проектом есть две подпапки testSearchInput и testStatInput в них есть тетовый входной 
 json для обеих типа запроса
 
