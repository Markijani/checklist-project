# Начало работы

Версия Java - 17

СУБД - PostgreSQL

В базу данных загружены все вопросы и все предложения по материалам для повторения, которые были в проекте.

## Структура данных
### Анкеты
Схема данных
```yaml
Анкета
  |__ id
  |__ username
  |__ groupNum
  |__ createdAt
  |__ result
  |__ answers
      |__ answer 0
          |__ id
          |__ question_level
          |__ question
          |__ value
      |__ answer 1
          |__ id
          |__ question_level
          |__ question
          |__ value
```

*Примечание:Результат анкеты считается в процентах, как отношение суммы всех оценок к максимальной сумме оценок (5 * количество вопросов).*

Пример Json
```json
 {
        "id": 7,
        "username": "Катя",
        "groupNum": "79",
        "createdAt": "2024-02-25T17:13:57.819715",
        "result": 61,
        "answers": [
            {
                "id": 121,
                "question_level": "beginner",
                "question": "Что такое TCP/IP, из каких уровней состоят и какие протоколы содержит. (В частности Ethernet, IP, TCP)",
                "value": 4
            },
            {
                "id": 122,
                "question_level": "beginner",
                "question": "Что такое HTML. Базовые элементы разметки. Семантика SEO-оптимизация и доступность",
                "value": 4
            },
            {
                "id": 123,
                "question_level": "beginner",
                "question": "Что такое CSS. Базовые свойства стилизации. Позиционирование элементов макета. Flexbox. Grid. Адаптивность посредством составления @media запросов. Псевдоэлементы и псевдоклассы. Методология БЭМ",
                "value": 3
            },
            {
                "id": 124,
                "question_level": "beginner",
                "question": "Основы языка программирования JavaScript. Типы данных. Циклы, Условия, Работа с объектами. Функции. Прототипы и наследование. Работа с объектами. Методы массивов",
                "value": 3
            },
            {
                "id": 125,
                "question_level": "beginner",
                "question": "Умеешь ли ты пользоваться программными средствами, аналогичными Slack, для организации коммуникации и совместной работы",
                "value": 4
            },
            {
                "id": 126,
                "question_level": "beginner",
                "question": "Имеешь ли ты практический опыт и навыки работы с инструментами, подобными Trello, для организации рабочих процессов и управления задачами",
                "value": 1
            },
            {
                "id": 127,
                "question_level": "trainee",
                "question": "Умеешь работать с браузерным окружением и DOM (Document Object Model). Браузерные события. Обработка ошибок (Try/Catch). Пользовательские ошибки. Модульная система. Экспорт и импорт. Web API",
                "value": 3
            },
            {
                "id": 128,
                "question_level": "trainee",
                "question": "Что делает JavaScript — асинхронным. Промисы. Fetch API. Синтаксический сахар — Async и Await",
                "value": 4
            },
            {
                "id": 129,
                "question_level": "trainee",
                "question": "Cross-Origin Resource Sharing (CORS). Request & Response Headers (это была тема для самостоятельного изучения)",
                "value": 3
            },
            {
                "id": 130,
                "question_level": "trainee",
                "question": "Что такое линтеры. Pre-commit Check",
                "value": 3
            },
            {
                "id": 131,
                "question_level": "trainee",
                "question": "Как работает система управления версиями (Git). Регистрация на GitHub. Коммиты. Репозитории. Merge Requests. Checkout. Ветки",
                "value": 3
            },
            {
                "id": 132,
                "question_level": "trainee",
                "question": "Что такое Node.js. Как работает менеджер пакетов Npm. package.json и package-lock.json. npm install, npm uninstall",
                "value": 3
            },
            {
                "id": 133,
                "question_level": "trainee",
                "question": "Что такое препроцессор. SASS и SCSS. Переменные. Импорты. Вложенность. Модули. Миксины",
                "value": 3
            },
            {
                "id": 134,
                "question_level": "trainee",
                "question": "Обладаешь ли ты навыками работы с редактором Figma или аналогичными инструментами в сфере дизайна или прототипирования",
                "value": 3
            },
            {
                "id": 135,
                "question_level": "trainee",
                "question": "Каков уровень вашего знакомства и опыта использования фреймворка Bootstrap и других подобных для разработки веб-интерфейсов",
                "value": 2
            },
            {
                "id": 136,
                "question_level": "junior",
                "question": "Что такое сборщик модулей. Babel. Настройка Webpack. Правила. Модули. Плагины. DevServer. Переход к Vite",
                "value": 4
            },
            {
                "id": 137,
                "question_level": "junior",
                "question": "Что такое React и какие проблемы он решает. Жизненный цикл компонентов. Особенности виртуального DOM-дерева. React компоненты. Базовые хуки. React Refs. JSX синтаксис. Props & State",
                "value": 3
            },
            {
                "id": 138,
                "question_level": "junior",
                "question": "Особенности FLUX архитектуры. Reducers. Actions. Хук useContext. Redux и Redux Toolkit. Redux Thunk. RTK Query. (По желанию можно изучить MobX и/или Zustand)",
                "value": 3
            },
            {
                "id": 139,
                "question_level": "junior",
                "question": "Что такое роут. Из чего состоит объект Location. Библиотека React Router",
                "value": 3
            },
            {
                "id": 140,
                "question_level": "junior",
                "question": "Как повысить эффективность стилизации. Что такое UI библиотека. MaterialUI",
                "value": 2
            }
        ]
    }
```
Схема данных
### Вопросы
```yaml
Вопрос
|__ id
|__ level
|__ included
|__ suggestions 
  |__ suggestion 0
    |__ id
    |__ name
    |__ link
  |__ suggestion 1
    |__ id
    |__ name
    |__ link
```

Пример Json

```json
 {
        "id": 1,
        "level": "beginner",
        "included": true,
        "suggestions": [
            {
                "id": 1,
                "name": "Неделя 19. Асинхронность и работа с API",
                "link": "https://itgirlschool.notion.site/19-API-f022a1d855f84556822792ae865ccc01"
            }
        ]
    }
```
## Запросы
### Работа с анкетами
#### Сохранение анкеты
Эндпоинт POST-запроса для сохранения анкеты
`
/form/create
`

Запрос возвращает анкету в json (см. выше пример json в разделе "Структура данных")

Body запроса - соответствует текущему коду проекта. В таком виде сейчас сохраняется в localStorage

```json 
{
  "userName": "Катя",
  "groupNum": "79",
  "beginner": {
    "setOfQuestions": [
      "Что такое TCP/IP, из каких уровней состоят и какие протоколы содержит. (В частности Ethernet, IP, TCP)",
      "Что такое HTML. Базовые элементы разметки. Семантика SEO-оптимизация и доступность",
      "Что такое CSS. Базовые свойства стилизации. Позиционирование элементов макета. Flexbox. Grid. Адаптивность посредством составления @media запросов. Псевдоэлементы и псевдоклассы. Методология БЭМ",
      "Основы языка программирования JavaScript. Типы данных. Циклы, Условия, Работа с объектами. Функции. Прототипы и наследование. Работа с объектами. Методы массивов",
      "Умеешь ли ты пользоваться программными средствами, аналогичными Slack, для организации коммуникации и совместной работы",
      "Имеешь ли ты практический опыт и навыки работы с инструментами, подобными Trello, для организации рабочих процессов и управления задачами"
    ],
    "currentRangeValues": [
      "4",
      "4",
      "3",
      "3",
      "4",
      "1"
    ]
  },
  "trainee": {
    "setOfQuestions": [
      "Умеешь работать с браузерным окружением и DOM (Document Object Model). Браузерные события. Обработка ошибок (Try/Catch). Пользовательские ошибки. Модульная система. Экспорт и импорт. Web API",
      "Что делает JavaScript — асинхронным. Промисы. Fetch API. Синтаксический сахар — Async и Await",
      "Cross-Origin Resource Sharing (CORS). Request & Response Headers (это была тема для самостоятельного изучения)",
      "Что такое линтеры. Pre-commit Check",
      "Как работает система управления версиями (Git). Регистрация на GitHub. Коммиты. Репозитории. Merge Requests. Checkout. Ветки",
      "Что такое Node.js. Как работает менеджер пакетов Npm. package.json и package-lock.json. npm install, npm uninstall",
      "Что такое препроцессор. SASS и SCSS. Переменные. Импорты. Вложенность. Модули. Миксины",
      "Обладаешь ли ты навыками работы с редактором Figma или аналогичными инструментами в сфере дизайна или прототипирования",
      "Каков уровень вашего знакомства и опыта использования фреймворка Bootstrap и других подобных для разработки веб-интерфейсов"
    ],
    "currentRangeValues": [
      "3",
      "4",
      "3",
      "3",
      "3",
      "3",
      "3",
      "3",
      "2"
    ]
  },
  "junior": {
    "setOfQuestions": [
      "Что такое сборщик модулей. Babel. Настройка Webpack. Правила. Модули. Плагины. DevServer. Переход к Vite",
      "Что такое React и какие проблемы он решает. Жизненный цикл компонентов. Особенности виртуального DOM-дерева. React компоненты. Базовые хуки. React Refs. JSX синтаксис. Props & State",
      "Особенности FLUX архитектуры. Reducers. Actions. Хук useContext. Redux и Redux Toolkit. Redux Thunk. RTK Query. (По желанию можно изучить MobX и/или Zustand)",
      "Что такое роут. Из чего состоит объект Location. Библиотека React Router",
      "Как повысить эффективность стилизации. Что такое UI библиотека. MaterialUI"
    ],
    "currentRangeValues": [
      "4",
      "3",
      "3",
      "3",
      "2"
    ]
  }
}

```
#### Получение всех анкет

Эндпоинт Get-запроса для получения всех анкет
`
/forms
`

Запрос возвращает массив анкет в json (см. выше пример json в разделе "Структура данных")
#### Получение анкет только по номеру группы

Эндпоинт Get-запроса анкет по номеру группы
`
/forms/group
`

Запрос возвращает массив анкет в json (см. выше пример json в разделе "Структура данных")

Пример запроса всех анкет группы "79"

`
/forms/group?group=79
`
#### Получение анкеты по номеру группы и имени

Эндпоинт Get-запроса  анкет по номеру группы и имени
`
/forms/groupAndName
`

Запрос возвращает массив анкет в json (см. выше пример json в разделе "Структура данных")

Пример запроса всех анкет группы "79" и девушек по имени "Катя"
`
/forms/groupAndName?group=79&name=Катя
`

#### Получение анкеты по id

Эндпоинт Get-запроса для получения анкеты по id
`
/form/{id}
`

Пример запроса с анкеты id "2"
`
/form/2
`

#### Удаление вопроса по id

Эндпоинт Delete-запроса
`
question/delete/{id}
`

Пример запроса для удаления вопроса с id "22"
`
question/delete/22
`
### Работа с вопросами
#### Сохранение вопроса

Эндпоинт POST-запроса для сохранения анкеты
`
/question/create
`

Запрос возвращает вопрос в json (см. выше пример json в разделе "Структура данных")

Пример Body запроса
```json
{
        "text": "Что такое HTML. Базовые элементы разметки. Семантика SEO-оптимизация и доступность",
        "included": true,
        "level": "beginner",
        "suggestions": [
            {
                "name": "Неделя 1. Основы HTML",
                "link": "https://itgirlschool.notion.site/1-HTML-db0c955498d94cef9962f9734c5b0ed2"
            },
            {
                "name": "Неделя 2. Семантическая вёрстка и разметка",
                "link": "https://itgirlschool.notion.site/2-c980fca3150f4b7890748c6cad913114"
            }
        ]
    }
```

#### Получение всех вопросов
Эндпоинт Get-запроса для получения всех вопросов
`
/questions
`

Запрос возвращает массив вопросов в json (см. выше пример json в разделе "Структура данных")
#### Получение вопросов, включенных или невключенных в опросник
Эндпоинт Get-запроса для получения всех вопросов
`
/question/included
`

Запрос возвращает массив вопросов в json (см. выше пример json в разделе "Структура данных")

Пример запроса всех вопросов включенных в опросник (то есть вопросов, где в поле included значение true)
`
/question/included?included=true
`

#### Получение вопроса по id

Эндпоинт Get-запроса для получения вопроса по id
`
/question/{id}
`

Пример запроса с id "2"
`
/question/2
`
#### Изменение текста вопроса, параметра included и уровня
Эндпоинт Put-запроса
`
question/delete/{id}
`

Пример Body запроса

```json
{
"id": 22,
"level": "trainee",
"included": false,
"text": "новый текст вопроса?"
}
```

Запрос возвращает вопрос в json (см. выше пример json в разделе "Структура данных")

#### Удаление вопроса по id

Эндпоинт Delete-запроса
`
question/delete/{id}
`

Пример запроса для удаления вопроса с id "22"
`
question/delete/22
`