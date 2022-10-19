# EuropeanRoulette

Что хочет заказчик:
    - Игра-desktop: европейская рулетка
    - Простой GUI. 
    - Игра против дилера. 
    - Игра на несколько игроков (сначала сделаем игру на 1 человека, позже добавим поддержку многопользовательской игры; нужно спросить у заказчика, есть ли ограничение на максимальное количество игроков) 

План работы:
    1) создать гитхаб репозиторий - ✅
         https://github.com/Fawentus/EuropeanRoulette
    
    2) продумать примерную архитектуру приложения - ✅
   
    3) придумать вид GUI - ✅
        - стартовое меню: выбор количества игроков и кнопка, запускающая игру
        - сама игра: в центре изображение рулетки (если останется время - добавить анимацию вращения), вся информация о ходе игры выводится текстовыми сообщениями на экран. Логика игры в первом релизе будет  упрощена: пользователю дается определенное количество баллов, а не фишек с разными номиналами, пользователь может делать ставку только на число или цвет. 
    
    4) разработка приложения:
        - используем практики экстремального программирования
        - сначала пишем простую консольную версию игры, предварительно написав тесты к базовому функционалу
        - добавляем простой GUI
        - каждом новом релизе добавляем небольшую часть функциональности


Что было сделано на каждой итерации (каждой итерации соответствует один коммит):

1)простая консольная игра, где пользователь выбирает 1 целое  число из диапазона от 0 и 36, а диллер говорит ему, выиграл ли или проиграл игрок. Ко всей функциональности написаны тесты.

2)добавлена возможность делать ставки на цвет выпавшего числа:
когда игрок ставит на цвет, он может выиграть столько же денег, сколько поставил. Если игрок поставил на число и угадал его, он получает в 36 раз больше денег, чем поставил. В дальнейшем, выбирать доступные виды ставок и коэффициенты выигрыша можно будет до старта игры. Написаны тесты.

3) начата работа над графическим интерфейсом: при старте приложения открывается окошко игры с одной кнопкой “начать игру”. При нажатии этой кнопки запускается игра в консоли.
