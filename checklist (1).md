# Анкеты

## Пример Json при запросе Анкеты
get-запрос /forms - все анкеты

get-запрос /form/1 - анкета с id 1. Выдает ответ с кодом 200 и json c полем message: "This form does not exist", если формы не существует

get-запрос /forms/qwertyui - анкеты с токеном qwertyui. Важно! Если вы положите две анкеты с одинаковыми токенами вернется ответ с сообщением "query did not return a unique result".  Выдает ответ с кодом 200 и json c полем message: "Form with this token does not exist", если формы не существует

В данной анкете - пройдено 2 темы (они отражены в "completedLevels"). К теме CSS, в которой дано менее 40 % правильных
ответов, дано предложение в "suggestions".

```json
 {
  "token": "12",
  "role": "user",
  "createdAt": "2024-04-01T16:13:59.114592",
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
  "answers": [
    {
      "id": 1,
      "question": "Что такое атрибут в HTML?",
      "answerText": "Это дополнительная информация, предназначенная для элементов HTML.",
      "correct": true
    },
    {
      "id": 4,
      "question": "Что такое семантика в HTML?",
      "answerText": "Это способ описания смысла информации на веб-странице с использованием правильных тегов.",
      "correct": true
    },
    {
      "id": 8,
      "question": "Какой тег используется для вставки изображения в HTML?",
      "answerText": "<img>",
      "correct": true
    },
    {
      "id": 10,
      "question": "Как связать label и input в HTML?",
      "answerText": "Связь между label и input устанавливается с помощью атрибута \"for\" у label и атрибута \"id\" у input.",
      "correct": true
    },
    {
      "id": 15,
      "question": "Для чего нужен валидатор в HTML?",
      "answerText": "Валидатор используется для проверки корректности структуры HTML-документа.",
      "correct": true
    },
    {
      "id": 16,
      "question": "Что такое CSS?",
      "answerText": "CSS - это язык программирования для создания интерактивных элементов на веб-странице.",
      "correct": false
    },
    {
      "id": 20,
      "question": "Из чего состоит блочная модель элемента?",
      "answerText": "Блочная модель элемента включает в себя содержимое элемента (content), отступы (padding), границы (border) и полосу прокрутки (margin).",
      "correct": true
    },
    {
      "id": 24,
      "question": "Как можно задать размер шрифта в CSS?",
      "answerText": "Размер шрифта нельзя задать в CSS.",
      "correct": false
    },
    {
      "id": 26,
      "question": "Как можно задать отступы вокруг элемента в CSS?",
      "answerText": "Отступы вокруг элемента нельзя задать в CSS.",
      "correct": false
    },
    {
      "id": 29,
      "question": "Каким образом можно управлять расположением элементов на веб-странице с помощью CSS?",
      "answerText": "Расположение элементов на веб-странице нельзя контролировать с помощью CSS.",
      "correct": false
    }
  ],
  "suggestions": [
    {
      "level": "Основы HTML",
      "name": "Тема сдана[позднее, возможно, заменим на название урока]",
      "link": null
    },
    {
      "level": "CSS",
      "name": "Тема не сдана[позднее, возможно, заменим на название урока]",
      "link": "ссылка"
    }
  ]
}
```

## Пример Json для отправки на бэк при создании Анкеты, если анкета ранее не была создана, с использованием текста вопросов и ответов
post-запрос /form/create - в headers - content type: application/json
```json
{
  "token": "12",
  "role": "user",
  "answers": [
    {
      "question": "Что такое атрибут в HTML?",
      "answerText": "Это дополнительная информация, предназначенная для элементов HTML."
    },
    {
      "question": "Что такое семантика в HTML?",
      "answerText": "Это способ описания смысла информации на веб-странице с использованием правильных тегов."
    },
    {
      "question": "Какой тег используется для вставки изображения в HTML?",
      "answerText": "<img>"
    },
    {
      "question": "Как связать label и input в HTML?",
      "answerText": "Связь между label и input устанавливается с помощью атрибута \"for\" у label и атрибута \"id\" у input."
    },
    {
      "question": "Для чего нужен валидатор в HTML?",
      "answerText": "Валидатор используется для проверки корректности структуры HTML-документа."
    },
    {
      "question": "Что такое CSS?",
      "answerText": "CSS - это язык программирования для создания интерактивных элементов на веб-странице."
    },
    {
      "question": "Из чего состоит блочная модель элемента?",
      "answerText": "Блочная модель элемента включает в себя содержимое элемента (content), отступы (padding), границы (border) и полосу прокрутки (margin)."
    },
    {
      "question": "Как можно задать размер шрифта в CSS?",
      "answerText": "Размер шрифта нельзя задать в CSS."
    },
    {
      "question": "Как можно задать отступы вокруг элемента в CSS?",
      "answerText": "Отступы вокруг элемента нельзя задать в CSS."
    },
    {
      "question": "Каким образом можно управлять расположением элементов на веб-странице с помощью CSS?",
      "answerText": "Расположение элементов на веб-странице нельзя контролировать с помощью CSS."
    }
  ]
}
```

## Пример Json для отправки на бэк при создании Анкеты, если анкета ранее не была создана, с использованием id ответов
post-запрос /form/createAnswId в headers - content type: application/json
```json
{
  "token": "gyhuji",
  "role": "user",
  "answersId": [
    1,
    4,
    7,
    10,
    13,
    16,
    19,
    22,
    25,
    28
  ]
}
```

## Пример Json для отправки на бэк при обновлении Анкеты с использованием текста вопросов и ответов
put-запрос /form/update в headers - content type: application/json

Выдает ответ с кодом 200 и json c полем message: "Form with this token does not exist", если формы не существует
Выдает ответ с кодом 200 и json c полем message: "This answer does not exist", если ответа не существует

Добавляются ответы по теме "Позиционирование"

```json
{
  "token": "yuiop[]l",
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

Выдает ответ с кодом 200 и json c полем message: "Form with this token does not exist", если формы не существует
Выдает ответ с кодом 200 и json c полем message: "This answer does not exist", если ответа не существует

Добавляются ответы по теме "Позиционирование"

```json
{
  "token": "4567890-",
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
