# Проект автотестов на мобильное приложение Wikipedia
Проект создан в рамках обучения в школе QA.GURU и представляет из себя часть выпускной работы.

<a name="оглавление"></a>
# Оглавление
1. [Технологии](#технологии)
2. [Описание проекта](#описание)
3. [Как запустить тесты](#запуск_локально)
   1. [Запуск тестов локально](#запуск_локально)
   2. [Запуск тестов в Jenkins](#запуск_дженкинс)
4. [Jenkins](#дженкинс)
5. [Результаты работы тестов](#видео)
   1. [Пример работы тестов (видео)](#видео)
   2. [Результаты работы тестов в Browserstack](#браузерстек)
   3. [Результаты тестов в телеграм](#телеграм)
6. [Allure TestOps](#проект)
   1. [Проект](#проект)
   2. [Интеграция с Jenkins](#интеграция)
   3. [Dashboard](#дашборд)

<a name="технологии"></a> 
# Использованы слудующие технологии:
<p align="center">
<img width="16%" title="Gradle" src="media/Gradle.svg">
<img width="16%" title="Java" src="media/Java.svg">
<img width="16%" title="JUnit5" src="media/JUnit5.svg">
<img width="16%" title="IntelliJ IDEA" src="media/Intelij_IDEA.svg">
<img width="16%" title="Selenide" src="media/Selenide.svg">
<img width="14%" title="Browserstack" src="media/BrowserstackLogo.svg">
<img width="16%" title="Android Studio" src="media/AndroidStudioLogo.svg">
<img width="16%" title="Allure Report" src="media/Allure_Report.svg">
<img width="16%" title="GitHub" src="media/GitHub.svg">
<img width="16%" title="Jenkins" src="media/Jenkins.svg">
<img width="15%" title="Allure TestOps" src="media/Allure-logo.svg">
</p>

[К оглавлению ⬆](#оглавление)
<a name="описание"></a> 
# Описание проекта
Автоматизирована проверка экрана онбординга в мобильном приложении Wikipedia.
Для конфигурации запуска теста используется библиотека Owner. С помощью нее настроен запуск на разных устройствах.

Конфигурация устройства задается в специальном файле, а при запуске тестов используется параметр для выбора конкретного файла конфигурации.
При этом, тесты могут работать без изменения кодовой базы:
- с удаленным устройством в Browserstack,
- с локальным эмулятором устройства через Android Studio,
- с реальным устройством подключенным к машине, где осуществляется запуск тестов.

[К оглавлению ⬆](#оглавление)
<a name="запуск_локально"></a> 
# Запуск тестов
Запуск осуществляется командой: 
```
gradle clean test -Ddevice=emulator
```
Где:
`-Ddevice` - параметр отвечающий за выбор устройства для запуска тестов. 
В значение данного параметра передается название файла настроек девайса. Например: "local". Тогда конфигуратор тестов
будет искать в ресурсах файл настроек с именем "local.properties" и использовать его для настройки девайса.

Если же передается "remote", то используются настройки для работы с Browserstack.

<a name="запуск_дженкинс"></a>
Для запуска тестов в Jenkins используется следующая команда:
```
clean
test
-DappUrl=${APPURL}
-DbrowserstackUrl=${BROWSERSTACKURL}
-Ddevice=remote
```
Где:

`-Ddevice=remote` - так как в Jenkins нет подключенных устройств, то тесты принудительно запускаются на проекте Browserstack,

`${APPURL}` - адрес-идентификатор приложения википедии в Browserstack,

`${BROWSERSTACKURL}` - адрес Browserstack.

[К оглавлению ⬆](#оглавление)
<a name="дженкинс"></a> 
# Запуск тестов в Jenkins выглядит следующим образом
Главная страница сборки
![](media/JenkinsJob.svg)

Выбор параметров сборки
![](media/JenkinsJobStart.svg)

Работа сборки
![](media/JenkinsJobWork.svg)

Отчет о выполнении тестов
![](media/AllureReport.svg)

Каждый тест, независимо от результата, состоит из:
- начальных параметров,
- шагов, 
- скриншота экрана,
- исходного кода страницы,
- видео выполнения теста.

![](media/AllureReportAll.svg)

Пример скриншота
![](media/AllureReportScreenshot.svg)

[К оглавлению ⬆](#оглавление)
<a name="видео"></a> 
# Пример прохождения теста на удаленной машине (клик для просмотра видео)
[![Watch the video](media/TestRun.gif)](https://app-automate.browserstack.com/sessions/229e141fc6d99625f9041a2b26caaef5911d5c92/video?token=Z1BHejVwcTAzUVZ1SThJaDVSVDdIMkVDcXgzSmpqSzJMT0VTTGtLVjV4WWZBdzNlTFJLdURzNkhGdmtNaExLc2VxSU5xR3JBYWF2ZFh1UGsrUUp5eUE9PS0tVEg2aGxTMVN5MmFIZGtvei9NU09GZz09--29e421f8e5e2fbbb127449f1ca57c8306f8348c7&source=rest_api&diff=2.566652564)

[К оглавлению ⬆](#оглавление)
<a name="браузерстек"></a> 
# Результаты работы тестов в Browserstack
![](media/BrowserstackRuns.svg)

![](media/BrowserstackRunResult.svg)

[К оглавлению ⬆](#оглавление)
<a name="телеграм"></a> 
# По результатам работы тестов отправляется краткий отчет в Telegram
![](media/TelegramBot.svg)

[К оглавлению ⬆](#оглавление)
<a name="проект"></a> 
# Создан проект в Allure TestOps
Тесты в проекте импортированы из кода, то есть не приходится писать тесты и автоматизировать их.
Достаточно написать автотест, а кейс в TMS всегда будет в актуальном состоянии. 
![](media/TestCases.svg)

[К оглавлению ⬆](#оглавление)
<a name="интеграция"></a> 
# Настроена интеграция Jenkins и Allure TestOps
Запуск джоб осуществляется из интерфейса Allure TestOps
![](media/AllureJobs.svg)

Результаты работы джоб также отображаются в Allure TestOps
![](media/LaunchedJobAllure.svg)

[К оглавлению ⬆](#оглавление)
<a name="дашборд"></a> 
# Настроен Dashboard с разными показателями
Отображаются графики тренда автоматизации, последний запуск и т.д.
![](media/Dashboard.svg)

[К оглавлению ⬆](#оглавление)
