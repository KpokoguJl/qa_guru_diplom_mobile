# Проект автотестов на мобильное приложение Wikipedia
Проект создан в рамках обучения в школе QA.GURU и представляет из себя часть выпускной работы.

Использованны слудующие технологии:
<p align="center">
<img width="16%" title="Gradle" src="media/Gradle.svg">
<img width="16%" title="Java" src="media/Java.svg">
<img width="16%" title="JUnit5" src="media/JUnit5.svg">
<img width="16%" title="IntelliJ IDEA" src="media/Intelij_IDEA.svg">
<img width="16%" title="Selenide" src="media/Selenide.svg">
<img width="16%" title="Android Studio" src="media/AndroidStudioLogo.svg">
<img width="16%" title="Allure Report" src="media/Allure_Report.svg">
<img width="16%" title="GitHub" src="media/GitHub.svg">
<img width="16%" title="Jenkins" src="media/Jenkins.svg">
<img width="14%" title="Browserstack" src="media/BrowserstackLogo.svg">
<img width="15%" title="Allure TestOps" src="media/Allure-logo.svg">
</p>

# Описание проекта
Автоматизирована проверка экрана онбординга в мобильном приложении Wikipedia.
Для конфигурации запуска теста используется библиотека Owner. С помощью нее настроен запуск на разных устройствах.

Конфигурация устройства задается в специальном файле, а при запуске тестов используется параметр для выбора конкретного файла конфигурации.
При этом, тесты могут работать без изменения кодовой базы:
- с удаленным устройством в Browserstack,
- с локальным эмулятором устройства через Android Studio,
- с реальным устройством подключенным к машине, где осуществляется запуск тестов.

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
- скриншота браузера,
- исходного кода страницы,
- лога консоли браузера,
- видео выполнения теста.

![](media/AllureReportAll.svg)

Пример скриншота
![](media/AllureReportScreenshot.svg)

# Пример прохождения теста на удаленной машине (клик для просмотра)
[![Watch the video](media/VideoPreview.svg)](media/TestRun.mp4)

# Результаты работы тестов в Browserstack
![](media/BrowserstackRuns.svg)

![](media/BrowserstackRunResult.svg)

# По резултатам работы тестов отправляется краткий отчет в Telegram
![](media/TelegramBot.svg)

# Создан проект в Allure TestOps
Тесты в проекте импортированы из кода, то есть не приходится писать тесты и автоматизировать их.
Достаточно написать автотест, а кейс в TMS всегда будет в актуальном состоянии. 
![](media/TestCases.svg)

# Настроена интеграция Jenkins и Allure TestOps
Запуск джоб осуществляется из интерфейса Allure TestOps
![](media/AllureJobs.svg)

Результаты работы джоб также отображаются в Allure TestOps
![](media/LaunchedJobAllure.svg)

# Настроен дашборд с разными показателями
Отображаются графики тренда автоматизации, последний запуск и т.д.
![](media/Dashboard.svg)

