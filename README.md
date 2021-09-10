# crazzyoffice

## Description
This project  how to process some commands via telegram bot. In my case  I control electrical relay on parking gateway . Easy to give access and maintain control users, view history etc. 
Otherwise for maintence it needs to open body of control panel , change control pins to enable program mode, add new wireless control button for every new  employee or if employee is fired 
or lost device, etc. In second, these buttons needs to buy and so on.
Electrical relay is represented by arduino ethernet board. I run web server on it and switch relay when get request to it from my app. The scheme of work was designed from the parts
that were available and looks loke below:
- User find telegram bot and start to communicate with it.
- Telegram bot procees request and send it to aplication
- If user is new it automaticly added to database and app reply with button what can send request to authorize
- To authorize user and manage list of users using fronted part of app what represent a table with ability to edit records.
- If user is authorized  then app reply with button what send request to enable an electrical  relay.
____
## Configuration
App is build on ***Spring MVC*** framework which contain simple ***Spring Security*** and ***Spring Data Jpa*** realisation. Your can change credentials in Security config file. By default username and 
password is *admin*.
Jpa config is loaded paramets from *persistence-mysql.properties* file. I used localy installed postgresql database.To create table use *init.sql* script. All actions logs in file that located in $HOME\Logs directory.

To create telegram bot use the main *BotFather* bot. When your get you botToken, your can set it in *telegram.properties* file. 
I didn't have ssl certificate to set WebHook so I used [ngrok](https://ngrok.com/) service what redirect query from bot to app. To handle request I used ready [TelegramBots](https://github.com/rubenlagus/TelegramBots)
project , where you can found many examples of how to use it.

To build project use maven. I deployed app on Apache Tomcat 9.0.

## Thanks all.



