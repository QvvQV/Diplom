# Дипломный проект профессии «Тестировщик»
В рамках дипломного проекта требовалось автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

База данных хранит информацию о заказах, платежах, статусах карт, способах оплаты.

Для покупки тура есть два способа: с помощью карты и в кредит. В приложении используются два отдельных сервиса оплаты: Payment Gate и Credit Gate.

[Ссылка на Дипломное задание.](https://github.com/netology-code/qa-diploma) 

# Тестовая документация
1. [План тестирования](https://github.com/QvvQV/Diplom/blob/main/Documents/Plan.md);
1. [Отчёт по итогам тестирования](https://github.com/QvvQV/Diplom/blob/main/Documents/Report.md);
1. [Отчет по итогам автоматизации](https://github.com/QvvQV/Diplom/blob/main/Documents/Summary.md)
# Запуск приложения
## Подготовительный этап
1. Установить и запустить IntelliJ IDEA;
1. Установать и запустить Docker Desktop;
1. Скопировать репозиторий с Github по ссылке.
1. Открыть проект в IntelliJ IDEA.
## Запуск тестового приложения
1. Запустить MySQL, PostgreSQL, NodeJS через терминал командой:
   
```
docker-compose up
```

2. В новой вкладке терминала запустить тестируемое приложение:

* Для MySQL:
  
```
java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
```

* Для PostgreSQL:
  
```
java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
```

3. Убедиться в готовности системы. Приложение должно быть доступно по адресу:
```
http://localhost:8080/
```
## Запуск тестов
В новой вкладке терминала запустить тесты:

1. Для MySQL:
```
gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
```
2. Для PostgreSQL:
```
gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
```
## Перезапуск тестов и приложения
Для остановки приложения в окне терминала нужно ввести команду Ctrl+С и повторить необходимые действия из предыдущих разделов.

## Формирование отчёта о тестировании
Предусмотрено формирование отчётности через Allure. Для этого в новой вкладке терминала вводим команду

```
.\gradlew allureServe
```
