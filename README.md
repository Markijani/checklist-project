# Анкеты

## Пример Json при запросе Анкеты
get-запрос /forms - все анкеты

get-запрос /form/1 - анкета с id 1. Выдает ответ с кодом 200 и json c полем message: "This form does not exist", если формы не существует

get-запрос form/uid?uid=sdfghjklhjkl - анкета с токеном sdfghjklhjkl для администратора. 

```json
{
    "id": 5,
    "uid": "sdfghjklhjkl",
    "name": "Маша",
    "surname": "Иванова",
    "email": "masha@gmail.com",
    "groupNum": 82,
    "createdAt": "2024-05-07T21:49:13.638009",
    "completedLevels": [
        {
            "id": 1,
            "name": "Основы HTML"
        },
        {
            "id": 2,
            "name": "CSS"
        }
    ],
    "weakTopics": [
        "CSS"
    ]
}
```
get-запрос form/uidUser?uid=sdfghjklhjkl - анкеты с токеном sdfghjklhjkl для пользователя. 
```json
{
    "id": 5,
    "uid": "sdfghjklhjkl",
    "name": "Маша",
    "surname": "Иванова",
    "email": "masha@gmail.com",
    "groupNum": 82,
    "completedLevels": [
        {
            "id": 2,
            "name": "CSS"
        },
        {
            "id": 1,
            "name": "Основы HTML"
        }
    ],
    "suggestions": [
        {
            "title": "Основы HTML",
            "links": ["ссылка1","ссылка2"],
            "wrongAnswers": [
            {
              "question": "Какой тег используется для вставки изображения в HTML?",
              "userAnswer": "<picture>",
              "rightAnswer": "<img>"
            },
            {
              "question": "Для чего нужен валидатор в HTML?",
              "userAnswer": "Валидатор используется для создания анимации на веб-странице.",
              "rightAnswer": "Валидатор используется для проверки корректности структуры HTML-документа."
            }
          ]
        }
    ]
}
```

Важно! Если вы положите две анкеты с одинаковыми токенами вернется ответ с сообщением "query did not return a unique result".  Выдает ответ с кодом 200 и json c полем message: "Form with this uid does not exist", если формы не существует

В данной анкете - пройдено 2 темы (они отражены в "completedLevels"). К теме CSS, в которой дано менее 40 % правильных
ответов, дано предложение в "suggestions" для пользователя, для админа она отмечена в "weakTopics". Неправильные ответы для пользователя "wrongAnswers". 

Если в анкете нет пройденных тем или предложений, или почты, или еще чего-то - там будет или пустой массив, или null. 

## Пример Json для отправки на бэк при создании Анкеты, если анкета ранее не была создана
post-запрос /form/create - в headers - content type: application/json


! ВНИМАНИЕ !!! Если в названии поля будет ошибка или не тот регистр буквы (то есть "UID:" - нельзя) - бэк или не отработает, или что-то сделает, но не то, что ожидалось.
Поэтому название полей, берите, из примеров ниже

! ВНИМАНИЕ ! groupNum передайте, пожалуйста, числовым типом, если строкой - сломается. Если не получится сделать число, то я переделаю в строук - сообщите мне. 

```json
{
    "uid": "sdfghjklhjkl",
    "name": "Маша",
    "surname": "Иванова",
    "groupNum": 82,
    "email": "masha@gmail.com"           
}
```

## Пример Json для отправки на бэк при обновлении Анкеты с использованием текста вопросов и ответов
put-запрос /form/update в headers - content type: application/json

Этот запрос медленнее, чем по id ответов. Если получится без него, то не стоит его использовать.

Кроме того, если хоть один лишний знак будет в ответе (знак препинания или буква другого регистра) - то в базе он не найдется (вернется ошибка - ответ не найден, см. ниже).

Выдает ответ с кодом 200 и json c полем message: "Form with this uid does not exist", если формы не существует
Выдает ответ с кодом 200 и json c полем message: "This answer does not exist", если ответа не существует

Добавляются ответы по теме "Позиционирование"

```json
{
  "uid": "yuiop[]l",
  "answers": [
    {
      "question": "Какое свойство CSS используется для задания позиции элемента в HTML документе?",
      "answerText": "position"
    },
    {
      "question": "Какой из следующих вариантов определяет, что элемент будет абсолютно позиционирован относительно его ближайшего позиционированного предка?",
      "answerText": "position: absolute"
    },
    {
      "question": "Какие значения можно использовать для свойства position в CSS?",
      "answerText": "top, left, right, bottom"
    },
    {
      "question": "Какое свойство CSS используется для задания позиции элемента в CSS Grid?",
      "answerText": "grid-column"
    },
    {
      "question": "Как можно выровнять элементы в адаптивной вёрстке?",
      "answerText": "Использовать CSS Grid"
    }
  ]
}
```

## Пример Json для отправки на бэк при обновлении Анкеты с использованием id ответов
put-запрос /form/updateAnswId в headers - content type: application/json

Выдает ответ с кодом 200 и json c полем message: "Form with this uid does not exist", если формы не существует
Выдает ответ с кодом 200 и json c полем message: "This answer does not exist", если ответа не существует

Добавляются ответы по теме "Позиционирование"

```json
{
  "uid": "4567890-",
  "answersId": [
    31,
    34,
    37,
    40,
    43
  ]
}
```

# Темы

## Пример Json при запросе всех тем с вопросами и ответами (сокращено до трех тем)
get-запрос /levelsAndQuestions - все темы с вопросами и ответами

get-запрос /level/1 -запрос темы по id 1. 

Выдает ответ с кодом 200 и json c полем message: "Level with this id does not exist", если уровня не существует

поля со значением null пока, что отображаются, ошибки нет (переиспользуется объект для передачи информации, можем
убрать, если мешает)

```json
[
  {
    "id": 1,
    "name": "Основы HTML",
    "questions": [
      {
        "id": 1,
        "level": null,
        "text": "Что такое атрибут в HTML?",
        "answers": [
          {
            "id": 2,
            "question": null,
            "answerText": "Это структурная единица HTML-документа.",
            "correct": false
          },
          {
            "id": 3,
            "question": null,
            "answerText": "Это видимый текст на веб-странице.",
            "correct": false
          },
          {
            "id": 1,
            "question": null,
            "answerText": "Это дополнительная информация, предназначенная для элементов HTML.",
            "correct": true
          }
        ]
      },
      {
        "id": 2,
        "level": null,
        "text": "Что такое семантика в HTML?",
        "answers": [
          {
            "id": 6,
            "question": null,
            "answerText": "Это метод форматирования текста в HTML.",
            "correct": false
          },
          {
            "id": 5,
            "question": null,
            "answerText": "Это способ создания анимации на веб-странице.",
            "correct": false
          },
          {
            "id": 4,
            "question": null,
            "answerText": "Это способ описания смысла информации на веб-странице с использованием правильных тегов.",
            "correct": true
          }
        ]
      },
      {
        "id": 3,
        "level": null,
        "text": "Какой тег используется для вставки изображения в HTML?",
        "answers": [
          {
            "id": 9,
            "question": null,
            "answerText": "<image>",
            "correct": false
          },
          {
            "id": 8,
            "question": null,
            "answerText": "<img>",
            "correct": true
          },
          {
            "id": 7,
            "question": null,
            "answerText": "<picture>",
            "correct": false
          }
        ]
      },
      {
        "id": 4,
        "level": null,
        "text": "Как связать label и input в HTML?",
        "answers": [
          {
            "id": 10,
            "question": null,
            "answerText": "Связь между label и input устанавливается с помощью атрибута \"for\" у label и атрибута \"id\" у input.",
            "correct": true
          },
          {
            "id": 12,
            "question": null,
            "answerText": "Связь между label и input устанавливается автоматически без дополнительных атрибутов.",
            "correct": false
          },
          {
            "id": 11,
            "question": null,
            "answerText": "Связь между label и input устанавливается с помощью атрибута \"name\" у label и атрибута \"id\" у input.",
            "correct": false
          }
        ]
      },
      {
        "id": 5,
        "level": null,
        "text": "Для чего нужен валидатор в HTML?",
        "answers": [
          {
            "id": 15,
            "question": null,
            "answerText": "Валидатор используется для проверки корректности структуры HTML-документа.",
            "correct": true
          },
          {
            "id": 13,
            "question": null,
            "answerText": "Валидатор используется для создания анимации на веб-странице.",
            "correct": false
          },
          {
            "id": 14,
            "question": null,
            "answerText": "Валидатор используется для создания адаптивного дизайна веб-страницы.",
            "correct": false
          }
        ]
      }
    ]
  },
  {
    "id": 2,
    "name": "CSS",
    "questions": [
      {
        "id": 6,
        "level": null,
        "text": "Что такое CSS?",
        "answers": [
          {
            "id": 18,
            "question": null,
            "answerText": "CSS - это язык для работы с базами данных на стороне клиента.",
            "correct": false
          },
          {
            "id": 17,
            "question": null,
            "answerText": "CSS (Cascading Style Sheets) - это язык стилей, используемый для оформления внешнего вида веб-страниц.",
            "correct": true
          },
          {
            "id": 16,
            "question": null,
            "answerText": "CSS - это язык программирования для создания интерактивных элементов на веб-странице.",
            "correct": false
          }
        ]
      },
      {
        "id": 7,
        "level": null,
        "text": "Из чего состоит блочная модель элемента?",
        "answers": [
          {
            "id": 21,
            "question": null,
            "answerText": "Блочная модель элемента включает в себя внутренние и внешние отступы (inner and outer margins), а также горизонтальную и вертикальную выравнивание (horizontal and vertical alignment).",
            "correct": false
          },
          {
            "id": 19,
            "question": null,
            "answerText": "Блочная модель элемента состоит из текста (text), изображений (images) и фонового изображения (background).",
            "correct": false
          },
          {
            "id": 20,
            "question": null,
            "answerText": "Блочная модель элемента включает в себя содержимое элемента (content), отступы (padding), границы (border) и полосу прокрутки (margin).",
            "correct": true
          }
        ]
      },
      {
        "id": 8,
        "level": null,
        "text": "Как можно задать размер шрифта в CSS?",
        "answers": [
          {
            "id": 22,
            "question": null,
            "answerText": "Размер шрифта можно задать с помощью свойства font-size, указав значение в пикселях, процентах или других единицах измерения.",
            "correct": true
          },
          {
            "id": 23,
            "question": null,
            "answerText": "Размер шрифта можно задать с помощью свойства font-family.",
            "correct": false
          },
          {
            "id": 24,
            "question": null,
            "answerText": "Размер шрифта нельзя задать в CSS.",
            "correct": false
          }
        ]
      },
      {
        "id": 9,
        "level": null,
        "text": "Как можно задать отступы вокруг элемента в CSS?",
        "answers": [
          {
            "id": 25,
            "question": null,
            "answerText": "Отступы вокруг элемента можно задать с помощью свойства border.",
            "correct": false
          },
          {
            "id": 26,
            "question": null,
            "answerText": "Отступы вокруг элемента нельзя задать в CSS.",
            "correct": false
          },
          {
            "id": 27,
            "question": null,
            "answerText": "Отступы вокруг элемента можно задать с помощью свойств margin или padding.",
            "correct": true
          }
        ]
      },
      {
        "id": 10,
        "level": null,
        "text": "Каким образом можно управлять расположением элементов на веб-странице с помощью CSS?",
        "answers": [
          {
            "id": 28,
            "question": null,
            "answerText": "Для управления расположением элементов на веб-странице используются свойства position, float, display и другие.",
            "correct": true
          },
          {
            "id": 29,
            "question": null,
            "answerText": "Расположение элементов на веб-странице нельзя контролировать с помощью CSS.",
            "correct": false
          },
          {
            "id": 30,
            "question": null,
            "answerText": "Расположение элементов на веб-странице автоматически определяется браузером.",
            "correct": false
          }
        ]
      }
    ]
  }
]
```

## Пример Json для отправки на бэк при обновлении Темы с использованием текста вопросов и ответов

В примере перезаписываем тему CSS.  Тема будет полностью перезаписана, поэтому даже если меняется только
часть вопросов/ответов/предложений - необходимо отправить и неизменяемые.

Выдает ответ с кодом 200 и json c полем message: "Level with this id does not exist", если уровня не существует


```json
{
  "id": 2,
  "name": "Новое название",
  "questions": [
    {
      "text": "Новый вопрос1?",
      "answers": [
        {
          "text": "Новый ответ1",
          "correct": false
        },
        {
          "text": "Новый ответ2",
          "correct": false
        },
        {
          "text": "Новый ответ3",
          "correct": true
        }
      ]
    },
    {
      "text": "Новый вопрос2?",
      "answers": [
        {
          "text": "Новый ответ1",
          "correct": false
        },
        {
          "text": "Новый ответ2",
          "correct": false
        },
        {
          "text": "Новый ответ3",
          "correct": true
        }
      ]
    }
  ],
  "suggestions": [
    {
      "name": "название урока",
      "link": "ссылка"
    }
  ]
}
```
