package com.sir.black.Tools.Special;

import com.badlogic.gdx.math.Vector2;

/**
 * Зіткнення обєктів
 * 11.01.18
 */

public class Interaction {
    // region fields
    /**
     * Швидкість першого мяча при якій мячик 2 стає системою відліку
     */
    protected static Vector2 ballnewSpeed1; // Швидкість першого мяча при якій мячик 2 стає системою відліку
    /**
     * Кут швидкості ballnewSpeed1 по відношенню до осі Ох
     */
    protected static float phi;  // Кут швидкості ballnewSpeed1 по відношенню до осі Ох
    /**
     * Кут між позиціями кульки 2, 2 і оссю Ох
     */
    protected static float ro; // Кут між позиціями кульки 2, 2 і оссю Ох
    /**
     * Нова позиція кульки 2 після зміщення на кут і зсуву до центру координат
     */
    protected static Vector2 ballPosition2Final; // Нова позиція кульки 2 після зміщення на кут і зсуву до центру координат
    /**
     * Вiдношення відстані яку мячик пройде до по відношенню до початкової швидкості
     */
    protected static float attachementPoint; // Вiдношення відстані яку мячик пройде до по відношенню до початкової швидкості
    /**
     * Точка зіткнення першої кулькои
     */
    protected static Vector2 ballHitPos1; // Точка зіткнення першої кулькои
    /**
     * Точка зіткнення другої кулькои
     */
    protected static Vector2 ballHitPos2; // Точка зіткнення другої кулькои
    /**
     * Кут між центром кульки 1 і початковим вектором швидкості кульки 1
     */
    protected static float angle1; // Кут між центром кульки 1 і початковим вектором швидкості кульки 1
    /**
     * Кут між центром кульки 2 і початковим вектором швидкості кульки 2
     */
    protected static float angle2; // Кут між центром кульки 2 і початковим вектором швидкості кульки 2
    // endregion

    //region external
    /**
     * Перевірка чи дві кульки одна на одну наложуються
     * @param ballPosition1 позиція кульки 1
     * @param radius1 радіус кульки 1
     * @param ballPosition2 позиція кульки 2
     * @param radius2 радіус кульки 2
     * @return повертає істину якщо перкрилися, повертає false якщо не перекриваються
     */
    public static boolean BallByBall(Vector2 ballPosition1, float radius1,
                                     Vector2 ballPosition2, float radius2) {
        return  (Math.sqrt(Math.pow(ballPosition1.x - ballPosition2.x, 2) + Math.pow(ballPosition1.y - ballPosition2.y, 2))
                < radius1 + radius2);
    }

    /**
     * Динамічне зіткнення двох обєктів з їх подальшим пружним росіюванням,
     * Мячі стикаються неперевним зіткненням і отримують абсолютно пружний удар
     * При цьому динамічно змінюються позиції обєктів і їх швидкості
     * Ця схема не є стійкою до потрійного зіткнення
     * @param ballPosition1 позиція обєкта номер 1
     * @param ballSpeed1 швидкість обєкта номер 1
     * @param radius1 радіус обєкта 1
     * @param mass1 маса обєкта 1
     * @param ballPosition2 позиція обєкта 2
     * @param ballSpeed2 швидкість обєкта номер 2
     * @param radius2 радіус обєкта номре 2
     * @param mass2 маса обєкта номре 2
     * @param dissapation коефіцієнт пружності. Виставляти від 0 до 1. Де 0 - абсолютно пружний удар, 1 - абсолютно непружний удар
     */
    public static boolean BallByBallElastic(Vector2 ballPosition1, Vector2 ballSpeed1, float radius1, float mass1,
                                            Vector2 ballPosition2, Vector2 ballSpeed2, float radius2, float mass2,
                                            float dissapation, Vector2 probabilityOfCoagulation, Vector2 hitPosition) {

        // Ідея розрахунку схеми заключається в тому щоб динамічно визначити зіткнення двох обєктів.
        // Спочатку визначаємо позицію зіткнення в часі(тобто долю цикла на який вони повинні були зіткнутися.
        // Кожне зіткнення відбулося між циклами, а нам треба функціонально визначити дробовий час між циклами
        // Тому зробимо функцію кожної змінної від часу, такими функціями будуть
        // (змінна - х1)ballPos1.x = ballPositionPrev1.x +  ballSpeed1.x * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // (змінна - y1)ballPos1.y = ballPositionPrev1.y +  ballSpeed1.y * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // (змінна - х2)ballPos2.x = ballPositionPrev2.x +  ballSpeed2.x * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // (змінна - y1)ballPos2.y = ballPositionPrev2.y +  ballSpeed2.y * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // Після чого підставимо значення цих функцій в рівнянн яке буде встановлювати точки в яких координати стали так щоб їх відстань була рівна двом радіусам
        // Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)) = radius1 + radius2
        // Далі підставимо змінні x1, x2, y1, y2 зверху
        // і розвяжемо квадратне рівняння відносно time
        // Після цього розрахуємо розльот за звичайною методикою пружного розльоту частинок

        // Минулі позиції обєктів
        Vector2 ballPositionPrev1 = new Vector2(ballPosition1.x - ballSpeed1.x, ballPosition1.y - ballSpeed1.y);
        Vector2 ballPositionPrev2 = new Vector2(ballPosition2.x - ballSpeed2.x, ballPosition2.y - ballSpeed2.y);
        if (new Vector2(ballPositionPrev1.x - ballPositionPrev2.x,
                ballPositionPrev1.y - ballPositionPrev2.y).len()
                >
                ballSpeed1.len() + ballSpeed2.len() + radius1 + radius2) {
            return SolveProblemWithCrossSection(false, ballPosition1, ballSpeed1, radius1, mass1,
                    ballPosition2, ballSpeed2, radius2, mass2,dissapation, probabilityOfCoagulation, hitPosition);
        }
        // Зведені різниці
        float xS = ballSpeed2.x - ballSpeed1.x;
        float x0 = ballPositionPrev2.x - ballPositionPrev1.x;
        float yS = ballSpeed2.y - ballSpeed1.y;
        float y0 = ballPositionPrev2.y - ballPositionPrev1.y;
        float rR = radius1 + radius2;
        probabilityOfCoagulation.x = 1; /////// !!!!!!!!!!!!!!!!
        // Зведені змінні 2 степеня зведеності, змінні для квадратного рівняння
        float a = xS*xS+yS*yS;
        if (a==0) // Якщо а == 0 то скорісті в них одинакові, а значить зіткення бути не може
        {
            return SolveProblemWithCrossSection(false, ballPosition1, ballSpeed1, radius1, mass1,
                    ballPosition2, ballSpeed2, radius2, mass2, dissapation, probabilityOfCoagulation, hitPosition);
        }
        float b = 2 * (xS*x0+yS*y0);
        float c = x0*x0+y0*y0-rR*rR;
        float D = b*b-4*a*c;
        if (D<0)
            return SolveProblemWithCrossSection(false, ballPosition1, ballSpeed1, radius1, mass1,
                    ballPosition2, ballSpeed2, radius2, mass2,dissapation, probabilityOfCoagulation, hitPosition);

        float x_0 = (-b - (float) Math.sqrt(D))/(2*a);
        float x_1 = (-b + (float) Math.sqrt(D))/(2*a);
        if ((x_0 < 0 || x_0 > 1) && (x_1 < 0 || x_1 > 1))
        {
            // Тут можливий випадок коли кульки знаходяться всередині одна одної
            /*if ((x_0 * x_1 < 0)&&
                    (radius1 + radius2 >
                            new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len()))
            {
                // Костиль в випадку коли дві кульки з центрами в одній точці
                if (ballPosition1.x == ballPosition2.x && ballPosition1.y == ballPosition2.y) {
                    ballPosition2.x += ZeroFun.frand(0.001f, 0.001f);
                    ballPosition2.y += ZeroFun.frand(0.001f, 0.001f);
                }

                // Якщо кульки вже пересікаються то для того щоб рознести їх треба додати відстань
                // цей параметр і є тією відстанню якої не вистачає для того щоб рознести кульки так щоб вони дотикалися
                float addtitonDistance = radius1 + radius2 -
                        new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len();

                // ** Пропорційно до маси розсовуємо кульки в різні сторони
                // Спочатку визначаємо напрямки на які будемо зміщати
                Vector2 newStartPosition1 = new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).nor();

                // Точка співудару
                hitPosition.x = newStartPosition1.x * radius1;
                hitPosition.y = newStartPosition1.y * radius1;
                // Ймовірність злиплення
                probabilityOfCoagulation.x = 1;
                probabilityOfCoagulation.y = 1;

                // Визначаємо відстань на яку будемо зміщати
                newStartPosition1 = new Vector2(
                        newStartPosition1.x * mass2/(mass1 + mass2) * addtitonDistance,
                        newStartPosition1.y * mass2/(mass1 + mass2) * addtitonDistance);
                // Визначаємо нову позицію
                ballPosition1.add(newStartPosition1);
                ballSpeed1.add(newStartPosition1.x/2, newStartPosition1.y/2);

                Vector2 newStartPosition2 = new Vector2(ballPosition2.x - ballPosition1.x, ballPosition2.y - ballPosition1.y).nor();
                newStartPosition2 = new Vector2(
                        newStartPosition2.x * mass1/(mass1 + mass2) * addtitonDistance,
                        newStartPosition2.y * mass1/(mass1 + mass2) * addtitonDistance);

                ballPosition2.add(newStartPosition2);
                ballSpeed2.add(newStartPosition2.x/2, newStartPosition2.y/2);

                //BallByBallElastic(ballPosition1, ballSpeed1, radius1, mass1,
                //                  ballPosition2, ballSpeed2, radius2, mass2, dissapation);
                return true;
            }*/
            return SolveProblemWithCrossSection(false, ballPosition1, ballSpeed1, radius1, mass1,
                    ballPosition2, ballSpeed2, radius2, mass2,dissapation, probabilityOfCoagulation, hitPosition);
        }
        else
        {
            // Знаходимо значення міжциклової часової точки коли кульки зіткнулися
            float x = Math.min((x_0 > 0 && x_0 < 1)?x_0:2, (x_1 > 0 && x_1 < 1)?x_1:2);
            // Точка зіткнення
            ballHitPos1 = new Vector2(ballPositionPrev1.x + ballSpeed1.x * x, ballPositionPrev1.y + ballSpeed1.y * x);
            ballHitPos2 = new Vector2(ballPositionPrev2.x + ballSpeed2.x * x, ballPositionPrev2.y + ballSpeed2.y * x);

            // Кут під яким стикаються два обєкта
            float collisAngle1 = (float)Math.atan2(ballHitPos1.y - ballHitPos2.y, ballHitPos1.x - ballHitPos2.x);
            float collisAngle2 = collisAngle1 + (float) Math.PI;//(float)Math.Atan2((ballHitPos2 - ballHitPos1).Y, (ballHitPos2 - ballHitPos1).X);

            //// Кут між центрами кульок і напрямком швидкості
            if (ballSpeed1.len() == 0) angle1 = 0;
            else
                angle1 = ((ballHitPos1.x - ballHitPos2.x) * ballSpeed1.x
                        + (ballHitPos1.y - ballHitPos2.y) * ballSpeed1.y)
                        / (ballSpeed1.len()
                        * new Vector2(ballHitPos1.x - ballHitPos2.x, ballHitPos1.y - ballHitPos2.y).len()); // Кути розльоту першого мяча


            if (ballSpeed2.len() == 0) angle2 = 0;
            else
                angle2 = (((ballHitPos2.x - ballHitPos1.x) * ballSpeed2.x
                        + (ballHitPos2.y - ballHitPos1.y) * ballSpeed2.y)
                        / (ballSpeed2.len()
                        * new Vector2(ballHitPos2.x - ballHitPos1.x, ballHitPos2.y - ballHitPos1.y).len())); // Кути розльоту другого мяча

            //// Отримаємо такі відповідні відльоти після удару
            float vs0x = ballSpeed1.x;
            float vs0y = ballSpeed1.y;
            float vs1x = (mass2 / mass1) * (float)Math.cos(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
            float vs1y = (mass2 / mass1) * (float)Math.sin(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
            float vs2x = (float)Math.cos(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));
            float vs2y = (float)Math.sin(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));;

            Vector2 v1 = new Vector2(vs0x-vs1x+vs2x, vs0y-vs1y+vs2y);
            vs0x = ballSpeed2.x;
            vs0y = ballSpeed2.y;
            vs1x = (mass1 / mass2) * (float)Math.cos(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));
            vs1y = (mass1 / mass2) * (float)Math.sin(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));

            vs2x = (float)Math.cos(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
            vs2y = (float)Math.sin(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));

            Vector2 v2 = new Vector2(vs0x-vs1x+vs2x, vs0y-vs1y+vs2y);

            // Нормування для того щоб енергії мячиків після зіткнення не мали або мали дисепацію
            float norma = (1 - dissapation) * (ballSpeed1.len() * mass1 + ballSpeed2.len() * mass2) / (v1.len() * mass1 + v2.len() * mass2);

            // Точкі коли мячі зікнуться
            Vector2 ballHitPosition1 = new Vector2(ballPositionPrev1.x + ballSpeed1.x * x, ballPositionPrev1.y + ballSpeed1.y * x);
            Vector2 ballHitPosition2 = new Vector2(ballPositionPrev2.x + ballSpeed2.x * x, ballPositionPrev2.y + ballSpeed2.y * x);


            // Точка співудару
            Vector2 ballHitVector = new Vector2(
                    (ballHitPosition1.x * radius2 + ballHitPosition2.x * radius1) / (radius1 + radius2),
                    (ballHitPosition1.y * radius2 + ballHitPosition2.y * radius1) / (radius1 + radius2));
            hitPosition.x = ballHitVector.x;
            hitPosition.y = ballHitVector.y;
            // Ймовірність злиплення
            float bigAngel;
            Vector2 sumSpeed = new Vector2(ballSpeed1.x + ballSpeed2.x, ballSpeed1.y + ballSpeed2.y);
            if (sumSpeed.len() == 0) bigAngel = 0;
            else
                bigAngel = ((ballHitPos1.x - ballHitPos2.x) * sumSpeed.x
                        + (ballHitPos1.y - ballHitPos2.y) * sumSpeed.y)
                        / (sumSpeed.len()
                        * new Vector2(ballHitPos1.x - ballHitPos2.x, ballHitPos1.y - ballHitPos2.y).len()); // Кути розльоту першого мяча
            probabilityOfCoagulation.x = bigAngel;
            probabilityOfCoagulation.y = (float) Math.acos(bigAngel);

            //// Змінює власні значення швидкостей обєкта
            ballSpeed1.x = v1.x * norma;// - ballSpeed1.x * x;
            ballSpeed1.y = v1.y * norma;// - ballSpeed1.y * x;
            ballSpeed2.x = v2.x * norma;// - ballSpeed2.x * x;
            ballSpeed2.y = v2.y * norma;// - ballSpeed2.y * x;

            ballPosition1.x = ballHitPosition1.x + ballSpeed1.x * (1-x);
            ballPosition1.y = ballHitPosition1.y + ballSpeed1.y * (1-x);
            ballPosition2.x = ballHitPosition2.x + ballSpeed2.x * (1-x);
            ballPosition2.y = ballHitPosition2.y + ballSpeed2.y * (1-x);
            return true;
        }
    }

    /**
     * Перевизначає деякі параметри(позицію і швидкість)
     * @param returnItem те що треба повернути
     * @param ballPosition1 позиція обєкта номер 1
     * @param ballSpeed1 швидкість обєкта номер 1
     * @param radius1 радіус обєкта 1
     * @param mass1 маса обєкта 1
     * @param ballPosition2 позиція обєкта 2
     * @param ballSpeed2 швидкість обєкта номер 2
     * @param radius2 радіус обєкта номре 2
     * @param mass2 маса обєкта номре 2
     * @param dissapation коефіцієнт пружності. Виставляти від 0 до 1. Де 0 - абсолютно пружний удар, 1 - абсолютно непружний удар
     * @param probabilityOfCoagulation ймовірність коагуляції
     * @param hitPosition позиція зіткнення
     * @return чи стикає чи не стикає
     */
    protected static boolean SolveProblemWithCrossSection(boolean returnItem,
                                                          Vector2 ballPosition1, Vector2 ballSpeed1, float radius1, float mass1,
                                                          Vector2 ballPosition2, Vector2 ballSpeed2, float radius2, float mass2,
                                                          float dissapation, Vector2 probabilityOfCoagulation, Vector2 hitPosition) {
        if (returnItem == false)
        {
            if (new Vector2(ballPosition1.x - ballPosition2.x,
                    ballPosition1.y - ballPosition2.y).len() < radius1 + radius2)
            {
                // Якщо кульки вже пересікаються то для того щоб рознести їх треба додати відстань
                // цей параметр і є тією відстанню якої не вистачає для того щоб рознести кульки так щоб вони дотикалися
                float addtitonDistance = radius1 + radius2 -
                        new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len();

                // ** Пропорційно до маси розсовуємо кульки в різні сторони
                // Спочатку визначаємо напрямки на які будемо зміщати
                Vector2 newStartPosition1 = new Vector2(ballPosition1.x - ballPosition2.x,
                        ballPosition1.y - ballPosition2.y).nor();

                // Точка співудару
                hitPosition.x = newStartPosition1.x * radius1;
                hitPosition.y = newStartPosition1.y * radius1;
                // Ймовірність злиплення
                probabilityOfCoagulation.x = 1;
                probabilityOfCoagulation.y = 1;

                // Визначаємо відстань на яку будемо зміщати
                newStartPosition1 = new Vector2(
                        newStartPosition1.x * mass2/(mass1 + mass2) * addtitonDistance,
                        newStartPosition1.y * mass2/(mass1 + mass2) * addtitonDistance);
                // Визначаємо нову позицію
                ballPosition1.add(newStartPosition1);
                ballSpeed1.add(newStartPosition1.x/2, newStartPosition1.y/2);

                Vector2 newStartPosition2 = new Vector2(ballPosition2.x - ballPosition1.x, ballPosition2.y - ballPosition1.y).nor();
                newStartPosition2 = new Vector2(
                        newStartPosition2.x * mass1/(mass1 + mass2) * addtitonDistance,
                        newStartPosition2.y * mass1/(mass1 + mass2) * addtitonDistance);

                ballPosition2.add(newStartPosition2);
                ballSpeed2.add(newStartPosition2.x/2, newStartPosition2.y/2);
            }
        }
        return returnItem;
    }
    //endregion

    // region Резерв
    /**
     * Динамічне зіткнення двох обєктів з їх подальшим пружним росіюванням,
     * Мячі стикаються неперевним зіткненням і отримують абсолютно пружний удар
     * При цьому динамічно змінюються позиції обєктів і їх швидкості
     * Ця схема не є стійкою до потрійного зіткнення
     * @param ballPosition1 позиція обєкта номер 1
     * @param ballSpeed1 швидкість обєкта номер 1
     * @param radius1 радіус обєкта 1
     * @param mass1 маса обєкта 1
     * @param ballPosition2 позиція обєкта 2
     * @param ballSpeed2 швидкість обєкта номер 2
     * @param radius2 радіус обєкта номре 2
     * @param mass2 маса обєкта номре 2
     * @param dissapation коефіцієнт пружності. Виставляти від 0 до 1. Де 0 - абсолютно пружний удар, 1 - абсолютно непружний удар
     */
    public static boolean BallByBallElastic0(Vector2 ballPosition1, Vector2 ballSpeed1, float radius1, float mass1,
                                             Vector2 ballPosition2, Vector2 ballSpeed2, float radius2, float mass2, float dissapation) {
        // Ідея розрахунку схеми заключається в тому щоб динамічно визначити зіткнення двох обєктів.
        // Спочатку визначаємо позицію зіткнення в часі(тобто долю цикла на який вони повинні були зіткнутися.
        // Кожне зіткнення відбулося між циклами, а нам треба функціонально визначити дробовий час між циклами
        // Тому зробимо функцію кожної змінної від часу, такими функціями будуть
        // (змінна - х1)ballPos1.x = ballPositionPrev1.x +  ballSpeed1.x * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // (змінна - y1)ballPos1.y = ballPositionPrev1.y +  ballSpeed1.y * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // (змінна - х2)ballPos2.x = ballPositionPrev2.x +  ballSpeed2.x * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // (змінна - y1)ballPos2.y = ballPositionPrev2.y +  ballSpeed2.y * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // Після чого підставимо значення цих функцій в рівнянн яке буде встановлювати точки в яких координати стали так щоб їх відстань була рівна двом радіусам
        // Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)) = radius1 + radius2
        // Далі підставимо змінні x1, x2, y1, y2 зверху
        // і розвяжемо квадратне рівняння відносно time
        // Після цього розрахуємо розльот за звичайною методикою пружного розльоту частинок

        // Минулі позиції обєктів
        Vector2 ballPositionPrev1 = new Vector2(ballPosition1.x - ballSpeed1.x, ballPosition1.y - ballSpeed1.y);
        Vector2 ballPositionPrev2 = new Vector2(ballPosition2.x - ballSpeed2.x, ballPosition2.y - ballSpeed2.y);
        // Зведені різниці
        float xS = ballSpeed2.x - ballSpeed1.x;
        float x0 = ballPositionPrev2.x - ballPositionPrev1.x;
        float yS = ballSpeed2.y - ballSpeed1.y;
        float y0 = ballPositionPrev2.y - ballPositionPrev1.y;
        float rR = radius1 + radius2;

        // Зведені змінні 2 степеня зведеності, змінні для квадратного рівняння
        float a = xS*xS+yS*yS;
        if (a==0) return false; // Якщо а == 0 то скорісті в них одинакові, а значить зіткення бути не може
        float b = 2 * (xS*x0+yS*y0);
        float c = x0*x0+y0*y0-rR*rR;

        float D = b*b-4*a*c;
        if (D<0) return false;

        float x_0 = (-b - (float) Math.sqrt(D))/(2*a);
        float x_1 = (-b + (float) Math.sqrt(D))/(2*a);

        if ((x_0 < 0 || x_0 > 1) && (x_1 < 0 || x_1 > 1))
        {
            // Тут можливий випадок коли кульки знаходяться всередині одна одної
            if ((x_0 * x_1 < 0)&&
                    (radius1 + radius2 >
                            new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len()))
            {
                // Костиль в випадку коли дві кульки з центрами в одній точці
                if (ballPosition1.x == ballPosition2.x && ballPosition1.y == ballPosition2.y) {
                    ballPosition2.x += ZeroFun.frand(0.001f, 0.001f);
                    ballPosition2.y += ZeroFun.frand(0.001f, 0.001f);
                }

                // Якщо кульки вже пересікаються то для того щоб рознести їх треба додати відстань
                // цей параметр і є тією відстанню якої не вистачає для того щоб рознести кульки так щоб вони дотикалися
                float addtitonDistance = radius1 + radius2 -
                        new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len();

                // ** Пропорційно до маси розсовуємо кульки в різні сторони
                // Спочатку визначаємо напрямки на які будемо зміщати
                Vector2 newStartPosition1 = new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).nor();
                // Визначаємо відстань на яку будемо зміщати
                newStartPosition1 = new Vector2(
                        newStartPosition1.x * mass2/(mass1 + mass2) * addtitonDistance,
                        newStartPosition1.y * mass2/(mass1 + mass2) * addtitonDistance);
                // Визначаємо нову позицію
                ballPosition1.add(newStartPosition1);
                ballSpeed1.add(newStartPosition1.x/2, newStartPosition1.y/2);

                Vector2 newStartPosition2 = new Vector2(ballPosition2.x - ballPosition1.x, ballPosition2.y - ballPosition1.y).nor();
                newStartPosition2 = new Vector2(
                        newStartPosition2.x * mass1/(mass1 + mass2) * addtitonDistance,
                        newStartPosition2.y * mass1/(mass1 + mass2) * addtitonDistance);

                ballPosition2.add(newStartPosition2);
                ballSpeed2.add(newStartPosition2.x/2, newStartPosition2.y/2);

                //BallByBallElastic(ballPosition1, ballSpeed1, radius1, mass1,
                //                  ballPosition2, ballSpeed2, radius2, mass2, dissapation);
                return true;
            }
            return false;
        }
        else
        {
            // Знаходимо значення міжциклової часової точки коли кульки зіткнулися
            float x = Math.min((x_0 > 0 && x_0 < 1)?x_0:2, (x_1 > 0 && x_1 < 1)?x_1:2);

            // Старий код
            ballHitPos1 = new Vector2(ballPositionPrev1.x + ballSpeed1.x * x, ballPositionPrev1.y + ballSpeed1.y * x);
            ballHitPos2 = new Vector2(ballPositionPrev2.x + ballSpeed2.x * x, ballPositionPrev2.y + ballSpeed2.y * x);

            // Кут під яким стикаються два обєкта
            float collisAngle1 = (float)Math.atan2(ballHitPos1.y - ballHitPos2.y, ballHitPos1.x - ballHitPos2.x);
            float collisAngle2 = collisAngle1 + (float) Math.PI;//(float)Math.Atan2((ballHitPos2 - ballHitPos1).Y, (ballHitPos2 - ballHitPos1).X);

            //// Кут між центрами кульок і напрямком швидкості
            if (ballSpeed1.len() == 0) angle1 = 0;
            else
                angle1 = ((ballHitPos1.x - ballHitPos2.x) * ballSpeed1.x
                        + (ballHitPos1.y - ballHitPos2.y) * ballSpeed1.y)
                        / (ballSpeed1.len()
                        * new Vector2(ballHitPos1.x - ballHitPos2.x, ballHitPos1.y - ballHitPos2.y).len()); // Кути розльоту першого мяча


            if (ballSpeed2.len() == 0) angle2 = 0;
            else
                angle2 = (((ballHitPos2.x - ballHitPos1.x) * ballSpeed2.x
                        + (ballHitPos2.y - ballHitPos1.y) * ballSpeed2.y)
                        / (ballSpeed2.len()
                        * new Vector2(ballHitPos2.x - ballHitPos1.x, ballHitPos2.y - ballHitPos1.y).len())); // Кути розльоту другого мяча

            //// Отримаємо такі відповідні відльоти після удару
            float vs0x = ballSpeed1.x;
            float vs0y = ballSpeed1.y;
            float vs1x = (mass2 / mass1) * (float)Math.cos(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
            float vs1y = (mass2 / mass1) * (float)Math.sin(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
            float vs2x = (float)Math.cos(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));
            float vs2y = (float)Math.sin(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));;

            Vector2 v1 = new Vector2(vs0x-vs1x+vs2x, vs0y-vs1y+vs2y);

            vs0x = ballSpeed2.x;
            vs0y = ballSpeed2.y;
            vs1x = (mass1 / mass2) * (float)Math.cos(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));
            vs1y = (mass1 / mass2) * (float)Math.sin(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));

            vs2x = (float)Math.cos(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
            vs2y = (float)Math.sin(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));

            Vector2 v2 = new Vector2(vs0x-vs1x+vs2x, vs0y-vs1y+vs2y);

            // Нормування для того щоб енергії мячиків після зіткнення не мали або мали дисепацію
            float norma = (1 - dissapation) * (ballSpeed1.len() * mass1 + ballSpeed2.len() * mass2) / (v1.len() * mass1 + v2.len() * mass2);

            // Точкі коли мячі зікнуться
            Vector2 ballHitPosition1 = new Vector2(ballPositionPrev1.x + ballSpeed1.x * x, ballPositionPrev1.y + ballSpeed1.y * x);
            Vector2 ballHitPosition2 = new Vector2(ballPositionPrev2.x + ballSpeed2.x * x, ballPositionPrev2.y + ballSpeed2.y * x);
            //// Змінює власні значення швидкостей обєкта
            ballSpeed1.x = v1.x * norma;// - ballSpeed1.x * x;
            ballSpeed1.y = v1.y * norma;// - ballSpeed1.y * x;
            ballSpeed2.x = v2.x * norma;// - ballSpeed2.x * x;
            ballSpeed2.y = v2.y * norma;// - ballSpeed2.y * x;

            ballPosition1.x = ballHitPosition1.x + ballSpeed1.x * (1-x);
            ballPosition1.y = ballHitPosition1.y + ballSpeed1.y * (1-x);
            ballPosition2.x = ballHitPosition2.x + ballSpeed2.x * (1-x);
            ballPosition2.y = ballHitPosition2.y + ballSpeed2.y * (1-x);
            return true;
        }
    }

    /** ((НЕ СТІЙКИЙ ДО ПОЯВЛЕННЯ ОБЄКТІВ В ОДИН ОДНОМУ))
     * Динамічне зіткнення двох обєктів з їх подальшим пружним росіюванням,
     * Мячі стикаються неперевним зіткненням і отримують абсолютно пружний удар
     * При цьому динамічно змінюються позиції обєктів і їх швидкості
     * Ця схема не є стійкою до потрійного зіткнення
     * @param ballPosition1 позиція обєкта номер 1
     * @param ballSpeed1 швидкість обєкта номер 1
     * @param radius1 радіус обєкта 1
     * @param mass1 маса обєкта 1
     * @param ballPosition2 позиція обєкта 2
     * @param ballSpeed2 швидкість обєкта номер 2
     * @param radius2 радіус обєкта номре 2
     * @param mass2 маса обєкта номре 2
     * @param dissapation коефіцієнт пружності. Виставляти від 0 до 1. Де 0 - абсолютно пружний удар, 1 - абсолютно непружний удар
     */
    public static boolean BallByBallElastic0(Vector2 ballPosition1, Vector2 ballSpeed1, float radius1, float mass1,
                                             Vector2 ballPosition2, Vector2 ballSpeed2, float radius2, float mass2,
                                             float dissapation, Vector2 probabilityOfCoagulation, Vector2 hitPosition) {

        // Ідея розрахунку схеми заключається в тому щоб динамічно визначити зіткнення двох обєктів.
        // Спочатку визначаємо позицію зіткнення в часі(тобто долю цикла на який вони повинні були зіткнутися.
        // Кожне зіткнення відбулося між циклами, а нам треба функціонально визначити дробовий час між циклами
        // Тому зробимо функцію кожної змінної від часу, такими функціями будуть
        // (змінна - х1)ballPos1.x = ballPositionPrev1.x +  ballSpeed1.x * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // (змінна - y1)ballPos1.y = ballPositionPrev1.y +  ballSpeed1.y * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // (змінна - х2)ballPos2.x = ballPositionPrev2.x +  ballSpeed2.x * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // (змінна - y1)ballPos2.y = ballPositionPrev2.y +  ballSpeed2.y * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
        // Після чого підставимо значення цих функцій в рівнянн яке буде встановлювати точки в яких координати стали так щоб їх відстань була рівна двом радіусам
        // Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)) = radius1 + radius2
        // Далі підставимо змінні x1, x2, y1, y2 зверху
        // і розвяжемо квадратне рівняння відносно time
        // Після цього розрахуємо розльот за звичайною методикою пружного розльоту частинок

        // Минулі позиції обєктів
        Vector2 ballPositionPrev1 = new Vector2(ballPosition1.x - ballSpeed1.x, ballPosition1.y - ballSpeed1.y);
        Vector2 ballPositionPrev2 = new Vector2(ballPosition2.x - ballSpeed2.x, ballPosition2.y - ballSpeed2.y);

        if (new Vector2(ballPositionPrev1.x - ballPositionPrev2.x,
                ballPositionPrev1.y - ballPositionPrev2.y).len()
                >
                ballSpeed1.len() + ballSpeed2.len() + radius1 + radius2) {
            return false;
        }
        // Зведені різниці
        float xS = ballSpeed2.x - ballSpeed1.x;
        float x0 = ballPositionPrev2.x - ballPositionPrev1.x;
        float yS = ballSpeed2.y - ballSpeed1.y;
        float y0 = ballPositionPrev2.y - ballPositionPrev1.y;
        float rR = radius1 + radius2;

        // Зведені змінні 2 степеня зведеності, змінні для квадратного рівняння
        float a = xS*xS+yS*yS;
        if (a==0) // Якщо а == 0 то скорісті в них одинакові, а значить зіткення бути не може
            return false;
        float b = 2 * (xS*x0+yS*y0);
        float c = x0*x0+y0*y0-rR*rR;

        float D = b*b-4*a*c;
        if (D<0)
            return false;

        float x_0 = (-b - (float) Math.sqrt(D))/(2*a);
        float x_1 = (-b + (float) Math.sqrt(D))/(2*a);

        if ((x_0 < 0 || x_0 > 1) && (x_1 < 0 || x_1 > 1))
        {
            // Тут можливий випадок коли кульки знаходяться всередині одна одної
            /*if ((x_0 * x_1 < 0)&&
                    (radius1 + radius2 >
                            new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len()))
            {
                // Костиль в випадку коли дві кульки з центрами в одній точці
                if (ballPosition1.x == ballPosition2.x && ballPosition1.y == ballPosition2.y) {
                    ballPosition2.x += ZeroFun.frand(0.001f, 0.001f);
                    ballPosition2.y += ZeroFun.frand(0.001f, 0.001f);
                }

                // Якщо кульки вже пересікаються то для того щоб рознести їх треба додати відстань
                // цей параметр і є тією відстанню якої не вистачає для того щоб рознести кульки так щоб вони дотикалися
                float addtitonDistance = radius1 + radius2 -
                        new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len();

                // ** Пропорційно до маси розсовуємо кульки в різні сторони
                // Спочатку визначаємо напрямки на які будемо зміщати
                Vector2 newStartPosition1 = new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).nor();

                // Точка співудару
                hitPosition.x = newStartPosition1.x * radius1;
                hitPosition.y = newStartPosition1.y * radius1;
                // Ймовірність злиплення
                probabilityOfCoagulation.x = 1;
                probabilityOfCoagulation.y = 1;

                // Визначаємо відстань на яку будемо зміщати
                newStartPosition1 = new Vector2(
                        newStartPosition1.x * mass2/(mass1 + mass2) * addtitonDistance,
                        newStartPosition1.y * mass2/(mass1 + mass2) * addtitonDistance);
                // Визначаємо нову позицію
                ballPosition1.add(newStartPosition1);
                ballSpeed1.add(newStartPosition1.x/2, newStartPosition1.y/2);

                Vector2 newStartPosition2 = new Vector2(ballPosition2.x - ballPosition1.x, ballPosition2.y - ballPosition1.y).nor();
                newStartPosition2 = new Vector2(
                        newStartPosition2.x * mass1/(mass1 + mass2) * addtitonDistance,
                        newStartPosition2.y * mass1/(mass1 + mass2) * addtitonDistance);

                ballPosition2.add(newStartPosition2);
                ballSpeed2.add(newStartPosition2.x/2, newStartPosition2.y/2);

                //BallByBallElastic(ballPosition1, ballSpeed1, radius1, mass1,
                //                  ballPosition2, ballSpeed2, radius2, mass2, dissapation);
                return true;
            }*/
            return false;
        }
        else
        {
            // Знаходимо значення міжциклової часової точки коли кульки зіткнулися
            float x = Math.min((x_0 > 0 && x_0 < 1)?x_0:2, (x_1 > 0 && x_1 < 1)?x_1:2);

            // Точка зіткнення
            ballHitPos1 = new Vector2(ballPositionPrev1.x + ballSpeed1.x * x, ballPositionPrev1.y + ballSpeed1.y * x);
            ballHitPos2 = new Vector2(ballPositionPrev2.x + ballSpeed2.x * x, ballPositionPrev2.y + ballSpeed2.y * x);

            // Кут під яким стикаються два обєкта
            float collisAngle1 = (float)Math.atan2(ballHitPos1.y - ballHitPos2.y, ballHitPos1.x - ballHitPos2.x);
            float collisAngle2 = collisAngle1 + (float) Math.PI;//(float)Math.Atan2((ballHitPos2 - ballHitPos1).Y, (ballHitPos2 - ballHitPos1).X);

            //// Кут між центрами кульок і напрямком швидкості
            if (ballSpeed1.len() == 0) angle1 = 0;
            else
                angle1 = ((ballHitPos1.x - ballHitPos2.x) * ballSpeed1.x
                        + (ballHitPos1.y - ballHitPos2.y) * ballSpeed1.y)
                        / (ballSpeed1.len()
                        * new Vector2(ballHitPos1.x - ballHitPos2.x, ballHitPos1.y - ballHitPos2.y).len()); // Кути розльоту першого мяча


            if (ballSpeed2.len() == 0) angle2 = 0;
            else
                angle2 = (((ballHitPos2.x - ballHitPos1.x) * ballSpeed2.x
                        + (ballHitPos2.y - ballHitPos1.y) * ballSpeed2.y)
                        / (ballSpeed2.len()
                        * new Vector2(ballHitPos2.x - ballHitPos1.x, ballHitPos2.y - ballHitPos1.y).len())); // Кути розльоту другого мяча

            //// Отримаємо такі відповідні відльоти після удару
            float vs0x = ballSpeed1.x;
            float vs0y = ballSpeed1.y;
            float vs1x = (mass2 / mass1) * (float)Math.cos(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
            float vs1y = (mass2 / mass1) * (float)Math.sin(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
            float vs2x = (float)Math.cos(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));
            float vs2y = (float)Math.sin(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));;

            Vector2 v1 = new Vector2(vs0x-vs1x+vs2x, vs0y-vs1y+vs2y);

            vs0x = ballSpeed2.x;
            vs0y = ballSpeed2.y;
            vs1x = (mass1 / mass2) * (float)Math.cos(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));
            vs1y = (mass1 / mass2) * (float)Math.sin(collisAngle1) *
                    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));

            vs2x = (float)Math.cos(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
            vs2y = (float)Math.sin(collisAngle2) *
                    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));

            Vector2 v2 = new Vector2(vs0x-vs1x+vs2x, vs0y-vs1y+vs2y);

            // Нормування для того щоб енергії мячиків після зіткнення не мали або мали дисепацію
            float norma = (1 - dissapation) * (ballSpeed1.len() * mass1 + ballSpeed2.len() * mass2) / (v1.len() * mass1 + v2.len() * mass2);

            // Точкі коли мячі зікнуться
            Vector2 ballHitPosition1 = new Vector2(ballPositionPrev1.x + ballSpeed1.x * x, ballPositionPrev1.y + ballSpeed1.y * x);
            Vector2 ballHitPosition2 = new Vector2(ballPositionPrev2.x + ballSpeed2.x * x, ballPositionPrev2.y + ballSpeed2.y * x);


            // Точка співудару
            Vector2 ballHitVector = new Vector2(
                    (ballHitPosition1.x * radius2 + ballHitPosition2.x * radius1) / (radius1 + radius2),
                    (ballHitPosition1.y * radius2 + ballHitPosition2.y * radius1) / (radius1 + radius2));
            hitPosition.x = ballHitVector.x;
            hitPosition.y = ballHitVector.y;
            // Ймовірність злиплення
            float bigAngel;
            Vector2 sumSpeed = new Vector2(ballSpeed1.x + ballSpeed2.x, ballSpeed1.y + ballSpeed2.y);
            if (sumSpeed.len() == 0) bigAngel = 0;
            else
                bigAngel = ((ballHitPos1.x - ballHitPos2.x) * sumSpeed.x
                        + (ballHitPos1.y - ballHitPos2.y) * sumSpeed.y)
                        / (sumSpeed.len()
                        * new Vector2(ballHitPos1.x - ballHitPos2.x, ballHitPos1.y - ballHitPos2.y).len()); // Кути розльоту першого мяча
            probabilityOfCoagulation.x = bigAngel;
            probabilityOfCoagulation.y = (float) Math.acos(bigAngel);

            //// Змінює власні значення швидкостей обєкта
            ballSpeed1.x = v1.x * norma;// - ballSpeed1.x * x;
            ballSpeed1.y = v1.y * norma;// - ballSpeed1.y * x;
            ballSpeed2.x = v2.x * norma;// - ballSpeed2.x * x;
            ballSpeed2.y = v2.y * norma;// - ballSpeed2.y * x;

            ballPosition1.x = ballHitPosition1.x + ballSpeed1.x * (1-x);
            ballPosition1.y = ballHitPosition1.y + ballSpeed1.y * (1-x);
            ballPosition2.x = ballHitPosition2.x + ballSpeed2.x * (1-x);
            ballPosition2.y = ballHitPosition2.y + ballSpeed2.y * (1-x);
            return true;
        }
    }
    /**
     * Динамічне зіткнення двох обєктів з їх подальшим пружним росіюванням,
     * Мячі стикаються неперевним зіткненням і отримують абсолютно пружний удар
     * При цьому динамічно змінюються позиції обєктів і їх швидкості
     * Ця схема не є стійкою до потрійного зіткнення
     * @param ballPosition1 позиція обєкта номер 1
     * @param ballSpeed1 швидкість обєкта номер 1
     * @param radius1 радіус обєкта 1
     * @param mass1 маса обєкта 1
     * @param ballPosition2 позиція обєкта 2
     * @param ballSpeed2 швидкість обєкта номер 2
     * @param radius2 радіус обєкта номре 2
     * @param mass2 маса обєкта номре 2
     * @param dissapation коефіцієнт пружності. Виставляти від 0 до 1. Де 0 - абсолютно пружний удар, 1 - абсолютно непружний удар
     *
    public static void BallByBallElastic(Vector2 ballPosition1, Vector2 ballSpeed1, float radius1, float mass1,
    Vector2 ballPosition2, Vector2 ballSpeed2, float radius2, float mass2, float dissapation) {
    // Ідея розрахунку схеми заключається в тому щоб динамічно визначити зіткнення двох обєктів.
    // Спочатку визначаємо позицію зіткнення в часі(тобто долю цикла на який вони повинні були зіткнутися.
    // Кожне зіткнення відбулося між циклами, а нам треба функціонально визначити дробовий час між циклами
    // Тому зробимо функцію кожної змінної від часу, такими функціями будуть
    // (змінна - х1)ballPos1.x = ballPositionPrev1.x +  ballSpeed1.x * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
    // (змінна - y1)ballPos1.y = ballPositionPrev1.y +  ballSpeed1.y * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
    // (змінна - х2)ballPos2.x = ballPositionPrev2.x +  ballSpeed2.x * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
    // (змінна - y1)ballPos2.y = ballPositionPrev2.y +  ballSpeed2.y * time(в коді це змінна 'х', цей параметр - час між циклами визначається від 0 до 1)
    // Після чого підставимо значення цих функцій в рівнянн яке буде встановлювати точки в яких координати стали так щоб їх відстань була рівна двом радіусам
    // Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)) = radius1 + radius2
    // Далі підставимо змінні x1, x2, y1, y2 зверху
    // і розвяжемо квадратне рівняння відносно time
    // Після цього розрахуємо розльот за звичайною методикою пружного розльоту частинок

    // Минулі позиції обєктів
    Vector2 ballPositionPrev1 = new Vector2(ballPosition1.x - ballSpeed1.x, ballPosition1.y - ballSpeed1.y);
    Vector2 ballPositionPrev2 = new Vector2(ballPosition2.x - ballSpeed2.x, ballPosition2.y - ballSpeed2.y);
    // Зведені різниці
    float xS = ballSpeed2.x - ballSpeed1.x;
    float x0 = ballPositionPrev2.x - ballPositionPrev1.x;
    float yS = ballSpeed2.y - ballSpeed1.y;
    float y0 = ballPositionPrev2.y - ballPositionPrev1.y;
    float rR = radius1 + radius2;

    // Зведені змінні 2 степеня зведеності, змінні для квадратного рівняння
    float a = xS*xS+yS*yS;
    if (a==0) return; // Якщо а == 0 то скорісті в них одинакові, а значить зіткення бути не може
    float b = 2 * (xS*x0+yS*y0);
    float c = x0*x0+y0*y0-rR*rR;

    float D = b*b-4*a*c;
    if (D<0) return;

    float x_0 = (-b - (float) Math.sqrt(D))/(2*a);
    float x_1 = (-b + (float) Math.sqrt(D))/(2*a);

    if ((x_0 < 0 || x_0 > 1) && (x_1 < 0 || x_1 > 1))
    {
    // Тут можливий випадок коли кульки знаходяться всередині одна одної
    if ((x_0 * x_1 < 0)&&
    (radius1 + radius2 >
    new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len()))
    {
    // Костиль в випадку коли дві кульки з центрами в одній точці
    if (ballPosition1.x == ballPosition2.x && ballPosition1.y == ballPosition2.y) {
    ballPosition2.x += ZeroFun.frand(0.001f, 0.001f);
    ballPosition2.y += ZeroFun.frand(0.001f, 0.001f);
    }

    // Якщо кульки вже пересікаються то для того щоб рознести їх треба додати відстань
    // цей параметр і є тією відстанню якої не вистачає для того щоб рознести кульки так щоб вони дотикалися
    float addtitonDistance = radius1 + radius2 -
    new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len();

    // ** Пропорційно до маси розсовуємо кульки в різні сторони
    // Спочатку визначаємо напрямки на які будемо зміщати
    Vector2 newStartPosition1 = new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).nor();
    // Визначаємо відстань на яку будемо зміщати
    newStartPosition1 = new Vector2(
    newStartPosition1.x * mass2/(mass1 + mass2) * addtitonDistance,
    newStartPosition1.y * mass2/(mass1 + mass2) * addtitonDistance);
    // Визначаємо нову позицію
    ballPosition1.add(newStartPosition1);

    Vector2 newStartPosition2 = new Vector2(ballPosition2.x - ballPosition1.x, ballPosition2.y - ballPosition1.y).nor();
    newStartPosition2 = new Vector2(
    newStartPosition2.x * mass1/(mass1 + mass2) * addtitonDistance,
    newStartPosition2.y * mass1/(mass1 + mass2) * addtitonDistance);

    ballPosition2.add(newStartPosition2);

    //BallByBallElastic(ballPosition1, ballSpeed1, radius1, mass1,
    //                  ballPosition2, ballSpeed2, radius2, mass2, dissapation);
    return;
    }
    }
    else
    {
    // Знаходимо значення міжциклової часової точки коли кульки зіткнулися
    float x = Math.min((x_0 > 0 && x_0 < 1)?x_0:2, (x_1 > 0 && x_1 < 1)?x_1:2);

    // Старий код
    ballHitPos1 = new Vector2(ballPositionPrev1.x + ballSpeed1.x * x, ballPositionPrev1.y + ballSpeed1.y * x);
    ballHitPos2 = new Vector2(ballPositionPrev2.x + ballSpeed2.x * x, ballPositionPrev2.y + ballSpeed2.y * x);

    // Кут під яким стикаються два обєкта
    float collisAngle1 = (float)Math.atan2(ballHitPos1.y - ballHitPos2.y, ballHitPos1.x - ballHitPos2.x);
    float collisAngle2 = collisAngle1 + (float) Math.PI;//(float)Math.Atan2((ballHitPos2 - ballHitPos1).Y, (ballHitPos2 - ballHitPos1).X);

    //// Кут між центрами кульок і напрямком швидкості
    if (ballSpeed1.len() == 0) angle1 = 0;
    else
    angle1 = ((ballHitPos1.x - ballHitPos2.x) * ballSpeed1.x
    + (ballHitPos1.y - ballHitPos2.y) * ballSpeed1.y)
    / (ballSpeed1.len()
     * new Vector2(ballHitPos1.x - ballHitPos2.x, ballHitPos1.y - ballHitPos2.y).len()); // Кути розльоту першого мяча


    if (ballSpeed2.len() == 0) angle2 = 0;
    else
    angle2 = (((ballHitPos2.x - ballHitPos1.x) * ballSpeed2.x
    + (ballHitPos2.y - ballHitPos1.y) * ballSpeed2.y)
    / (ballSpeed2.len()
     * new Vector2(ballHitPos2.x - ballHitPos1.x, ballHitPos2.y - ballHitPos1.y).len())); // Кути розльоту другого мяча

    //// Отримаємо такі відповідні відльоти після удару
    float vs0x = ballSpeed1.x;
    float vs0y = ballSpeed1.y;
    float vs1x = (mass2 / mass1) * (float)Math.cos(collisAngle2) *
    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
    float vs1y = (mass2 / mass1) * (float)Math.sin(collisAngle2) *
    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
    float vs2x = (float)Math.cos(collisAngle1) *
    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));
    float vs2y = (float)Math.sin(collisAngle1) *
    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));;

    Vector2 v1 = new Vector2(vs0x-vs1x+vs2x, vs0y-vs1y+vs2y);

    vs0x = ballSpeed2.x;
    vs0y = ballSpeed2.y;
    vs1x = (mass1 / mass2) * (float)Math.cos(collisAngle1) *
    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));
    vs1y = (mass1 / mass2) * (float)Math.sin(collisAngle1) *
    (ballSpeed2.len() * (2 * mass2 / (mass1 + mass2)) * (Math.abs(angle2)));

    vs2x = (float)Math.cos(collisAngle2) *
    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));
    vs2y = (float)Math.sin(collisAngle2) *
    (ballSpeed1.len() * (2 * mass1 / (mass1 + mass2)) * (Math.abs(angle1)));

    Vector2 v2 = new Vector2(vs0x-vs1x+vs2x, vs0y-vs1y+vs2y);

    // Нормування для того щоб енергії мячиків після зіткнення не мали або мали дисепацію
    float norma = (1 - dissapation) * (ballSpeed1.len() * mass1 + ballSpeed2.len() * mass2) / (v1.len() * mass1 + v2.len() * mass2);

    // Точкі коли мячі зікнуться
    Vector2 ballHitPosition1 = new Vector2(ballPositionPrev1.x + ballSpeed1.x * x, ballPositionPrev1.y + ballSpeed1.y * x);
    Vector2 ballHitPosition2 = new Vector2(ballPositionPrev2.x + ballSpeed2.x * x, ballPositionPrev2.y + ballSpeed2.y * x);
    //// Змінює власні значення швидкостей обєкта
    ballSpeed1.x = v1.x * norma;// - ballSpeed1.x * x;
    ballSpeed1.y = v1.y * norma;// - ballSpeed1.y * x;
    ballSpeed2.x = v2.x * norma;// - ballSpeed2.x * x;
    ballSpeed2.y = v2.y * norma;// - ballSpeed2.y * x;

    ballPosition1.x = ballHitPosition1.x + ballSpeed1.x * (1-x);
    ballPosition1.y = ballHitPosition1.y + ballSpeed1.y * (1-x);
    ballPosition2.x = ballHitPosition2.x + ballSpeed2.x * (1-x);
    ballPosition2.y = ballHitPosition2.y + ballSpeed2.y * (1-x);
    }
    }
     */
    // endregion

    // region Метод який не використовується, для пошуку чи дві кульки зіткнулися взагалі
    /**
     * Неперервне зіткнення типу рухомий мячик - рухомий мячик, показує чи мячики перетнулися
     * @param ballPosition1 Позиція першого мяча
     * @param ballSpeed1 Швидкість першого мяча
     * @param radius1 Радіус першого мяча
     * @param ballPosition2 Позиція другого мяча
     * @param ballSpeed2 Швидкість другого мяча
     * @param radius2 Радіус другого мяча
     * @return Чи мячі перетнулися
     *
    /* public static boolean BallByBall(Vector2 ballPosition1, Vector2 ballSpeed1, float radius1, Vector2 ballPosition2, Vector2 ballSpeed2, float radius2) {
    if (new Vector2(ballPosition1.x + ballSpeed1.x - ballPosition2.x - ballSpeed2.x,
    ballPosition1.y + ballSpeed1.y - ballPosition2.y - ballSpeed2.y).len() < radius1 + radius2) return true;

    if (new Vector2(ballPosition1.x - ballPosition2.x, ballPosition1.y - ballPosition2.y).len() < radius1 + radius2) return true;

    ballnewSpeed1 = new Vector2(ballSpeed1.x - ballSpeed2.x, ballSpeed1.y - ballSpeed2.y);
    phi = (float)Math.atan2(ballnewSpeed1.y, ballnewSpeed1.x);
    RO = (float)Math.atan2(ballPosition2.y - ballPosition1.y, ballPosition2.x - ballPosition1.y);
    ballPosition2Final = new Vector2(new Vector2(ballPosition2.x - ballPosition1.x, ballPosition2.y - ballPosition1.y).len() * (float)Math.cos(RO - phi),
    new Vector2(ballPosition2.x - ballPosition1.x, ballPosition2.y - ballPosition1.y).len() * (float)Math.sin(RO - phi));
    if ((ballPosition2Final.x >= 0) && (ballPosition2Final.x <= ballnewSpeed1.len()) && (radius1 + radius2 >= Math.abs(ballPosition2Final.y))) return true;
    else return false;
    }*/
    // endregion
}
