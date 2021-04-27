-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-04-2021 a las 16:56:24
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gargolasproject`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Nota1` (IN `id` INT, IN `plan` INT, `resultado` INT)  BEGIN
INSERT INTO semana_1 VALUES(id,plan,1,resultado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarnotas` (IN `plan` INT, IN `resultado1` INT, IN `resultado2` INT, IN `resultado3` INT, IN `resultado4` INT)  BEGIN
DECLARE res integer;
DECLARE codigo5 integer;
DECLARE codigo1 integer;
DECLARE codigo2 integer;
DECLARE codigo3 integer;
DECLARE codigo4 integer;
DECLARE enun VARCHAR(50);

SET codigo5 = (SELECT  (MAX(IdResultado)+1) FROM resultados1);
SET codigo1 = (SELECT  (MAX(IdSemana1)+1) FROM semana_1);
SET codigo2 = (SELECT (MAX(IdSemana2)+1)FROM semana_2);
SET codigo3 = (SELECT  (MAX(IdSemana3)+1) FROM semana_3);
SET codigo4 = (SELECT  (MAX(IdSemana4)+1) FROM semana_4);


SET res=((resultado1+resultado2+resultado3+resultado4)/4);
IF (res>=60)
THEN SET enun = 'Aprobado';
ELSE
SET enun = 'Reprobado';
END IF;
INSERT INTO semana_1 VALUES (codigo1, plan, 1, resultado1);
INSERT INTO semana_2 VALUES (codigo2, plan, 2, resultado2);
INSERT INTO semana_3 VALUES (codigo3, plan, 3, resultado3);
INSERT INTO semana_4 VALUES (codigo4, plan, 4, resultado4);
INSERT INTO resultados1 VALUES(codigo5,codigo1, codigo2, codigo3,codigo4, res, enun);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejercicios`
--

CREATE TABLE `ejercicios` (
  `IdEjercicios` int(11) NOT NULL COMMENT 'Identificacion del ejercicios',
  `Nombre_Ejercicio` varchar(50) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Nombre del ejercicio',
  `Descripcion` varchar(500) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Descripción de cada ejercicio, especificaciones de preparacion y ejecucion del mismo',
  `Categoria_Ejercicio` enum('Acrobacia','Animacion','Baile','E_Introductorios','Gimnasia') COLLATE latin1_spanish_ci NOT NULL COMMENT 'Identificador numerico de la categoria '
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla donde se almacenan los registros de ejercicios';

--
-- Volcado de datos para la tabla `ejercicios`
--

INSERT INTO `ejercicios` (`IdEjercicios`, `Nombre_Ejercicio`, `Descripcion`, `Categoria_Ejercicio`) VALUES
(137, 'Dismount', 'Es el movimiento final desde un elemento de pareja o pirámide hacia un cradle o a la superficie de presentación. Los movimientos son considerados “Desmontes” si se liberan hacia un cradle, o si se liberan y asisten hacia la superficie de presentación. El movimiento desde un cradle hacia la superficie de presentación no es considerado un  “Desmonte”.', 'Acrobacia'),
(139, 'Flatback', 'Es un elemento de pareja en la cual la Top o persona 	que 	sube 	se 	encuentra acostada horizontalmente, boca arriba y es usualmente sostenida por dos o más bases. En  Latinoamérica se enseñó como  “Dead  man”  o  muertito ', 'Acrobacia'),
(140, 'Dive Roll ', 'Es un elemento de gimnasia aéreo con un rollo al frente, en donde los pies dejan el suelo antes de que las manos del atleta alcance la superficie del suelo. CO. Pescadito', 'Gimnasia'),
(141, 'Front Limber', 'Un Elemento de gimnasia no­aéreo en el cual se efectúa una rotación hacia el frente pasando por una posición invertida para llegar a una posición no invertida, arqueando piernas y la cadera sobre la cabeza para caer de pie sobre la superficie de presentación, con ambas piernas/pies  al  mismo  tiempo. ', 'Gimnasia'),
(142, 'Front Tuck', 'Un elemento de  Acrobacia/gimnasia en donde el atleta logra generar momentum o impulso hacia arriba para poder realizar un mortal agrupado al frente. (T ambién conocido como “punch front”  o  rebote  mortal )  ', 'Gimnasia'),
(143, 'Handspring /  “Flic flac” ', 'Un elemento de gimnasia aéreo en el cual un atleta se impulsa desde los pies y salta hacia a adelante o hacia atrás rotando a través de una posición de parada de manos. El atleta entonces empuja desde sus manos poniendo el peso sobre los brazos y usandolos como un impulso desde los hombros para volver a caer sobre sus pies, completando  la  rotación. ', 'Gimnasia'),
(146, 'Dismount / Desmonte', 'Es el movimiento final desde un elemento de pareja o pirámide hacia un cradle o a la superficie de presentación. Los movimientos son considerados “Desmontes” si se liberan hacia un cradle, o si se liberan y asisten hacia la superficie de presentación. El movimiento desde un cradle hacia la superficie de presentación no es considerado un  “Desmonte”.', 'Acrobacia'),
(212, 'Base', 'Base: Es una  Persona que está con el peso y en contacto directo con la superficie de presentación, que le brinda soporte a otra persona. La(s) persona(s) que sostiene(n), levanta(n) o lanza(n) a una top hacia un elemento de pareja. (ver también: Bases nuevas y/o Base Original). Si hubiese solamente una persona por debajo del pie de la persona o top que sube, sin importar la posición, la persona es considerada como  una  base. ', 'Acrobacia'),
(214, 'Chair / Silla', ' Es un elemento o pareja al nivel del pecho en la cual la(s) base(s) soportan el tobillo de la Top (persona que sube) con una mano y la otra mano sirve de soporte en forma de asiento para la top, La pierna que se soporta debe de estar en posición vertical debajo del torso de la persona que sube, simulando una posición en la que la top está sentada como en una silla. ', 'Acrobacia'),
(216, 'Coed Style Toss / Parejas', 'Un elemento de pareja de una sola base donde la base toma a su “top” (P ersona que sube ) al nivel de la cadera y la lanza a  sus  manos  desde  el  suelo.  Ch.  Dupla ', 'Acrobacia'),
(218, 'Cradle / Cuna', 'Es un desmonte en el cual la Top o persona que sube es recibida  en  una  posición  de  cuna. ', 'Acrobacia'),
(220, 'Cradle Position / Posición de Cuna: ', 'La(s) base(s), soportan a la Top colocando los brazos en la espalda y por debajo de las piernas.  La top, o persona que sube debe caer en postura de pike/hollow, simulando una “V” (viendo hacia arriba, piernas rectas y juntas),  por  debajo  del  nivel  del  pecho. ', 'Acrobacia'),
(222, 'Cupie ', 'Pareja extendida, donde la “top” se encuentra en una posición vertical (de pie), y tiene sus dos pies juntos sobre la(s) mano(s) de la(s) base(s). Se le  llama  también  “Awesome”. ', 'Acrobacia'),
(224, 'Dismount / Desmonte', 'Es el movimiento final desde un elemento de pareja o pirámide hacia un cradle o a la superficie de presentación. Los movimientos son considerados “Desmontes” si se liberan hacia un cradle, o si se liberan y asisten hacia la superficie de presentación. El movimiento desde un cradle hacia la superficie de presentación no es considerado un “Desmonte”. Cuando/si se realiza un elemento de cradle hacia la superficie de presentación el elemento debe de seguir las reglas de parejas (Giros,  Transiciones', 'E_Introductorios'),
(226, 'Downward Inversión /  Inversión Hacia Abajo', ' Es un elemento de pareja o pirámide en la cual la top o persona que sube se encuentra  en posición invertida y su centro de gravedad se mueve hacia la superficie de  presentación. ', 'Acrobacia'),
(228, 'Extended Stunt / Pareja  Extendida ', 'Cuando el cuerpo entero de la Top o persona que sube está por encima del nivel  de  la  cabeza  de  la(s) base(s)  Ejemplos de “Parejas Extendidas”: Extensión, Liberty Extendido, Cupie extendido. Ejemplos  de parejas que no son considerados “Parejas extendidas”: Chairs, Torches, Flat Backs (muertito), Arm­n­arms, y Stradle Lifts (Spagat o Teddy Bear). Mortales suspendidos y saltos de rana (Estos son parejas en donde los brazos de las bases están extendidos por encima de su cabeza, pero NO son co', 'Acrobacia'),
(229, 'Flatback /  Tablita ­ Muertito ', 'Es un elemento de pareja en la cual la Top o persona 	que 	sube 	se 	encuentra acostada horizontalmente, boca arriba y es usualmente sostenida por dos o más bases. En  Latinoamérica se enseñó como  “Dead  man”  o  muertito ', 'Acrobacia'),
(230, 'Flip (Stunting) / Mortal en Parejas', ' Un elemento de pareja que involucra un movimiento 	de 	rotación 	de cadera ­sobre­cabeza 	sin 	contacto alguno con la superficie de presentación o sus bases, mientras el cuerpo pasa por  la  posición  invertida. ', 'Acrobacia'),
(231, 'Hanging Pyramid /  Pirámide Colgante o Pirámide su', 'Una pirámide en donde una o más personas están suspendidas de la superficie de presentación por una o más “tops”, o persona que sube. “Una pirámide colgante”, podria ser considerada como una pirámide de 2 pisos y ½ de altura tomando en cuenta que el peso de la persona en la parte superior  nace  desde  el  segundo  nivel.  ', 'Acrobacia'),
(232, 'Helicopter /  Helicóptero', 'Es una pareja o elemento en donde la top, en posición horizontal es lanzada para ejecutar una rotación alrededor del eje vertical (simulando  las aspas de un helicóptero ) para ser recibida por sus bases  originales.  ', 'E_Introductorios'),
(233, 'Initiation/Initiating/Iniciación/Ini ciando', 'El comienzo de un elemento? el punto desde el cual se origina. El punto de iniciación para uno o más elemento(s) de construcción: Pareja, pirámide transición, movimiento suelto, desmonte o lanzamiento es la parte inferior del movimiento más bajo (Dip) desde  el  cual  se  origina  el  elemento.  ', 'Acrobacia'),
(234, 'Inversión / Inversión', 'Ver \"Inverted / Invertido\"? es el acto de estar en posición  invertida.  ', 'Acrobacia'),
(235, 'Liberty / Libertad', 'Una pareja en la cual la(s) base(s) sostienen un pie de la Top, o persona que sube mientras el otro pie se encuentra al lado de la otra rodilla  con  la  pierna  doblada.  ', 'Acrobacia'),
(236, 'Load­In / Entrada o Montaje', ' Una posición de pareja en la cual la Top o persona que sube tiene al menos un pie en la(s) mano(s) de la(s) Base(s). La(s) mano(s) de la(s) bases están a la altura de  la  cadera.  ', 'Acrobacia'),
(237, 'Multi­Based  Stunt  /  Parejas  Multi Base', ' Es una pareja que tiene 2 o más bases sin  incluir  el  Spotter  de  atrás.  ', 'Acrobacia'),
(238, 'Platform Position / Posición Plataforma', 'Un elemento o pareja de una sola pierna en donde la otra pierna de la Top no tiene soporte y está estirada al lado de la pierna que tiene soporte. También se le conoce como “Dangle”  o  “target  position” ', 'Acrobacia'),
(239, 'Prep (stunt) /  Parejas de nivel ½ o prep', 'Un elemento multibase, una pareja de dos piernas en la cual la Top, o persona que sube se encuentra al nivel de los hombros sostenida por sus bases en una posición  vertical. ', 'E_Introductorios'),
(240, 'Prep­Level / Nivel Medio', 'La conexión más baja entre la(s) base(s) y la top (persona que sube) es por encima del nivel de la cadera y por debajo del nivel extendido. Ej. Prep, Hitch (enganche) al nivel de los hombros. Una pareja también se puede considerar al nivel del pecho/prep, si el/los brazo(s) de la(s) base(s) están extendidos sobre la cabeza, pero NO se considera “Parejas Extendidas” tomando en cuenta que la altura del cuerpo de la Top o persona que sube es similar al de la pareja al nivel del pecho /hombro Ej. Fl', 'Acrobacia'),
(241, 'Pyramid  /  Pirámide', 'Dos  o  más elementos  de  parejas  conectadas. ', 'Acrobacia'),
(242, 'Rewind  / Toss  Mortal :  ', 'Movimiento libre  de  Mortal  desde  el  nivel  del  suelo utilizado  como  un  elemento  de  entrada hacia  una  pareja.  Se   le  llama  Toss Mortal,  o  lanzamiento  de  mortal  a una  pareja \n ', 'Acrobacia'),
(243, 'Show  and  Go', 'Es  una  transición  de parejas  en  la  cual  la  pareja  pasa  a través  de  una  posición  en  el  nivel extendido  y  retorna  a  una  posición  de pareja  no  extendida ( bolita). ', 'E_Introductorios'),
(244, 'Shoulder Level / Nivel de Hombros', 'Sección entre la(s) base(s) y la Top (persona que sube) que está a la altura de  los  hombros  de  la(s)  Base(s) ', 'Acrobacia'),
(245, 'Shoulder Sit / Sentada en los Hombros', 'Una pareja en la cual la Top, o persona que sube se sienta en los hombros de una base. Esto es considerado una pareja del nivel del pecho  o  prep  level  stunt ', 'Acrobacia'),
(246, 'Shoulder Stand / Parada en los hombros', 'Una pareja en la cual la Top, o persona que sube está de pie sobre los  hombros  de  una  base. ', 'Acrobacia'),
(247, 'Shushunova', 'Salto en straddle (toe touch) cayendo sobre la superficie de presentación en posición prona o de lagartija  (Push  Up). Toe   touch  facial ', 'Acrobacia'),
(248, 'Single­Based Double Cupie /  Doble Cupie en Una So', 'Una sola base soportando a 2 “tops” (personas  que suben ) las cuales tienen ambos pies en cada mano de la base simulando cada una de ellas un cupie (Awesome)? ver  definición  de  “Cupie”. ', 'Acrobacia'),
(249, 'Single­Leg  Stunt ', 'Single­Leg  Stunt  /  Pareja  de  una sola  Pierna:  Ver  \"Stunt  /  Pareja\". ', 'E_Introductorios'),
(250, 'Standing Tumbling / Gimnasia estática ', 'Elemento(s) o habilidades de gimnasia (o serie de elemento(s)). Ejecutados desde una posición estática, sin haber hecho ningún movimiento previo hacia el frente. No importa la cantidad de pasos realizados hacia atrás antes de efectuar una destreza gimnasta, se define como “gimnasia estática”. ', 'Acrobacia'),
(251, 'Straight Cradle /  cuna directo', 'Un mov. suelto o libre desde una pareja hacia una posición de cradle/cuna donde la Top/Flyer mantiene su cuerpo desde una posición de “salida directa” (Straight Right) no se puede ejectuar ningun elemento extra (ej. Turn (cambio de dirección) Kick (patada), Twist (giro), Pretty  Girl  (Chica  Linda),  Etc) ', 'E_Introductorios'),
(252, 'Suspended Roll / Rollo Suspendido', 'Es un elemento de pareja que involucra una rotación de la cadera sobre la cabeza por parte de la Top mientras esté conectada con mano/Muñeca y Mano/Muñeca, de su(s) base(s) que se encuentran sobre la superficie de presentación. La(s) base(s) tendrán sus brazos extendidos durante la rotación del elemento. La rotación de la Top es limitada de cualquier forma hacia delante  o  hacia  atrás ', 'Acrobacia'),
(253, 'Suspended Backward Roll / Rollo suspendido hacia a', 'Un rollo suspendido con una rotación hacia atrás. Ver Rollo suspendido /Suspended \nRoll.  ', 'Acrobacia'),
(254, 'T­Lift / Alzada en T', 'Una Pareja en la cual la Top con sus brazos en una posición de “T­Motion” recibe soporte en ambos lados de su cuerpo por dos bases que conectan cada una las manos y por debajo de los brazos de la top. La top o persona que sube permanece en una posición no invertida, y en posición vertical mientras estan siendo soportada en  la  pareja.  ', 'Baile'),
(255, 'Three Quarter (3/4) Front Flip (stunt) /  Mortal d', 'Una rotación de sobre la cabeza hacia delante, en la cual la Top/Flyer, es liberada de una posición erguida  hacia  una  posición  de  cradle.  ', 'Acrobacia'),
(256, 'Toe/Leg Pitch', 'Pareja de una o múltiples bases en donde la(s) base(s) empuja(n) o lanza(n) hacia arriba una pierna o pie de la top para incrementar la  altura  de  la  top  o  persona  que  sube. ', 'E_Introductorios'),
(257, 'Transitional Pyramid /  Transición de pirámide', 'Una Top o persona que sube moviéndose de una posición a otra en la pirámide. La transición, puede involucrar cambio de bases con al menos un atleta al nivel del pecho o menos, que mantenga contacto constante con la Top, o persona que sube.  ', 'Acrobacia'),
(258, 'Transitional Stunt /  Transición de Pareja', 'Top o persona(s) que sube(n) moviéndose de una posición de la pareja a otra, cambiando así la configuración de la pareja Inicial. Cada punto de inicio se usa para determinar el inicio de una transición. El final de la transición es definida como un nuevo punto de inicio. Cuando se detiene el movimiento, y/o la persona que sube (top) hace contacto con la superficie de presentación', 'Acrobacia'),
(259, 'Twisting Stunt / Pareja con giro', 'Cualquier transición con giro que involucre a la Top y su(s) base(s). El grado del giro es determinado por el total de rotación(es) continuas de la de la Top en relación con la superficie de presentación. El giro será medido utilizando ambos “El eje vertical” (De la cabeza a los Pies) y “El eje Horizontal” (A través del ombligo en una posición no erguida). La rotación simultánea sobre un eje vertical u horizontal deben considerarse de manera separada, no acumulativa, cuando se determine el grado', 'Acrobacia'),
(260, 'Two and One Half (2­1/2)–High Pyramid ­ Pirámide d', ' Una Pirámide en la cual la Top o Persona(s) que suben mantienen el peso (sin conectarse) sobre al menos una Top o persona y la top se encuentra libre de contacto de la(s) base(s). La altura corporal de la pirámide para “Dos y un medio” es medida por la altura de un cuerpo de la siguiente manera. Chairs, Tighs Stand (parada en el muslo) y Shoulder Stradles (spagats) tienen ½ de Altura del cuerpo. Las paradas sobre los hombros (shoulder Stand) son tomados como 2 cuerpos de altura? parejas extendi', 'Acrobacia'),
(261, 'V­Sit / Spagat (Teddy Bear)', 'La postura de una Top o persona que sube, cuando se sienta en una pareja con sus piernas estiradas, y paralelas a la superficie de presentación en una posición  de  “V” ', 'Baile'),
(262, 'Aerial (noun)/Aéreo (Elemento aéreo sin manos)', 'Rueda de carreta o árabe al frente sin poner las manos en el  suelo. ', 'Gimnasia'),
(263, 'Backbend (Stunting)', 'El cuerpo del atleta en forma de arco, normalmente apoyado por las manos y pies con el abdomen  hacia  arriba  o  hacia  el  cielo.  ', 'E_Introductorios'),
(264, 'Back Walkover /  Árabe hacia Atrás', 'Un elemento en gimnasia no­aérea en donde la persona se arquea hacia atrás, buscando el contacto de las manos con el suelo primero, para luego rotar la cadera sobre la cabeza y que el peso se traslade  sobre  un  pie/pierna  a  la  vez. ', 'Gimnasia'),
(265, 'Backward Roll/Rollo Atrás', 'Elemento de gimnasia no­aéreo en donde se presenta una rotación hacia atrás pasando por/a través de una posición invertida, levantando la cadera sobre los hombros y la cabeza, mientras la espalda se encorva, creando un movimiento en posición de bolita para crear un movimiento similar al de una bola,  “rodando”  sobre  el  suelo. ', 'Gimnasia'),
(266, 'Barrel  Roll  /  Rollo  de  Barril ', 'Barrel Roll/ Rollo de Barril con rotación lateral : Ver “Rollo de Tronco”. ', 'Gimnasia'),
(267, 'Block Cartwheel / Vuelta Carreta con empuje', 'Una rueda de carreta, o media luna, con movimiento aéreo temporal, realizada por el gimnasta al empujar a través de los hombros contra el suelo (superficie de presentación) durante  la  ejecución  del  elemento. ', 'Gimnasia'),
(268, 'Braced Flip: (Mortal Conectado) ', 'Un elemento de pareja en la cual la top realiza una rotación de cadera­sobre­cabeza mientras mantiene el contacto con otra(s) top(s) o personas  que  suben.  ', 'Gimnasia'),
(269, 'Cartwheel / Vuelta de carreta o voltereta lateral', 'Es un elemento de gimnasia no­aéreo donde el Atleta soporta el peso del cuerpo con el/los brazo(s) mientras se rota lateralmente a través de una posición invertida cayendo en  un  pie  a  la  vez. CO:   media  luna.  ', 'Gimnasia'),
(270, 'Dive Roll /  Salto del Tigre – rollo adelante exte', 'Es un elemento de gimnasia aéreo con un rollo al frente, en donde los pies dejan el suelo antes de que las manos del atleta alcance la superficie del suelo. CO. Pescadito ', 'Gimnasia'),
(271, 'Double Cartwheel /  Doble rueda de Carreta', 'Doble Rueda de Carreta o Doble Media Luna, Es un elemento de pareja invertida con un compañero (partner), con un agarre o conexión de Mano/tobillo o Brazo/muslo hecha de manera  simultánea. ', 'Gimnasia'),
(272, 'Flip (Tumbling) / Mortal en Gimnasia', 'Es un elemento de gimnasia que involucra un movimiento de rotación de cadera ­sobre­cabeza sin contacto alguno con la superficie de presentación, mientras el cuerpo pasa por  la  posición  invertida. ', 'Gimnasia'),
(273, 'Forward Roll / Rollo  al Frente', 'Elemento en gimnasia no­aéreo en la cual se rota hacia el frente a través de una posición invertida, levantando la cadera para que pase por encima de la cabeza y hombros, mientras encorva la espalda para crear un movimiento similar al de una bola “rodando” por el piso. ', 'Gimnasia'),
(274, 'Front Limber /  Paloma', 'Un Elemento de gimnasia no­aéreo en el cual se efectúa una rotación hacia el frente pasando por una posición invertida para llegar a una posición no invertida, arqueando piernas y la cadera sobre la cabeza para caer de pie sobre la superficie de presentación, con ambas piernas/pies  al  mismo  tiempo. ', 'Gimnasia'),
(275, 'Front Tuck /  Mortal al Frente', 'Un elemento de  Acrobacia/gimnasia en donde el atleta logra generar momentum o impulso hacia arriba para poder realizar un mortal agrupado al frente. (T ambién conocido como “punch front”  o  rebote  mortal )  ', 'Gimnasia'),
(276, 'Front Walkover /  Árabe al Frente', 'Un elemento de gimnasia no­aéreo en el cual se efectúa una rotación hacia el frente pasando por una posición invertida para llegar a una posición no invertida, arqueando piernas y cadera sobre la cabeza para caer de pie sobre la superficie de presentación con un pie/pierna  a  la  vez. ', 'Gimnasia'),
(277, 'Handspring /  “Flic flac” ', 'Un elemento de gimnasia aéreo en el cual un atleta se impulsa desde los pies y salta hacia a adelante o hacia atrás rotando a través de una posición de parada de manos. El atleta entonces empuja desde sus manos poniendo el peso sobre los brazos y usandolos como un impulso desde los hombros para volver a caer sobre sus pies, completando  la  rotación. ', 'Gimnasia'),
(278, 'Handstand /  Parada de Manos', ' Es una p osición invertida, donde los brazos del atleta están totalmente extendidos y pegados  a  la  cabeza  y  orejas. ', 'Gimnasia'),
(279, 'Layout /  Plancha o mortal extendido ', 'Un elemento o habilidad aérea de gimnasia en donde se involucra una rotación de la cabeza sobre la cadera en una p osición extendida del cuerpo, en forma recta y apretada, “hollow” (con  el pecho hundido )  o  levemente  arqueado. ', 'Gimnasia'),
(280, 'Log Roll / Rollo de Tronco', 'Es un mov. suelto donde el cuerpo de la top gira al menos 360 grados mientras se encuentra paralela a la superficie de presentación. Un Rollo de barril asistido podría ser el mismo elemento, con asistencia de una base adicional que mantenga contacto a través de la transición.  ', 'Gimnasia'),
(281, 'Nugget', 'Posición fetal en la cual el atleta esta en una posición de bolita con sus manos y rodillas sobre la superficie de presentación. Cuando un atleta en posición de Nugget sirve de soporte a una Top, esta se considera como una base  de  pareja  al  nivel  de  la  cadera. ', 'Baile'),
(282, 'Onodi', 'Iniciando desde un “Flic Flac” hacia atrás, y después de empujar con las manos del suelo, el gimnasta ejecuta una rotación en su eje vertical, de ½ (medio) giro, hacia las manos, finalizando el elemento hacia un flic flac hacia adelante con step out, o pierna hacia  el  ', 'Gimnasia'),
(283, '87.      ·  Punch  Front ', 'Ver  “Front  Tuck  /  mortal hacia  el  frente”. ', 'Gimnasia'),
(284, 'Round  Off  / Rondada ', ' Similar  a  la Rueda  de  carreta  (Cartwheel)  a excepción  de  que  el  atleta  finaliza  el elemento  con  ambos  pies  juntos  sobre la  superficie  de  presentación  en  vez  de una  pierna  a  la  vez,  cambiando  de dirección  desde  la  cual  inició  elelemento.  ', 'Gimnasia'),
(285, 'Tic­Tock', 'Una pareja sujetada en posición estática sobre una pierna en la cual, la(s) base(s) usando piernas hacen una media sentadilla hacia abajo y sueltan a la persona cuando van subiendo, mientras la top cambia su pie de manera erguida, poniendo el peso sobre el otro pie, y cae en posición estática  sobre  su  pie  contrario.  ', 'Gimnasia'),
(286, 'Tuck Position / Posición de Bolita', 'Una posición en la cual el cuerpo se dobla al nivel de la cadera/ llevando las rodillas  hacia  el  torso  /  pecho.  ', 'Gimnasia'),
(287, 'Twist / Giro', 'Un atleta ejecutando una rotación alrededor del eje longitudinal o eje vertical del cuerpo (Eje Vertical = eje  desde  la  cabeza  hasta  los  pies).  ', 'Gimnasia'),
(288, 'Walkover / Árabe', 'Elemento de gimnasia no aéreo que involucra una rotación de sobre la cabeza en la cual la persona genera una rotación hacia delante o hacia atrás (usualmente ejecutado con las piernas en una posición de splits) con soporte de una o ambas  manos.  ', 'Gimnasia'),
(289, 'Whip / Tempo', 'Un elemento sin rotación de giro, que se traslada hacia atrás en un movimiento de gimnasia aérea en la cual los pies del atleta rota por encima de su cabeza y su cuerpo, mientras el cuerpo permanece  una posición estirada en la parte superior de la espalda , Un Whip o Tempo se mira como  un  flic  Flac  sin  manos. ', 'E_Introductorios'),
(290, 'Ball – X', 'Posición del cuerpo (usualmente durante un lanzamiento) donde la “flyer” (persona lanzada) va de una posición en bolita o agrupada a una posición de Straddle / posición de “X”. Con los brazos y piernas o solo las piernas. También se le denomina “X” Out. ', 'Acrobacia'),
(291, 'Basket Toss /  Lanzamiento de canastas', 'Un lanzamiento que involucra 2 o 3 bases y un spotter. 2 de las bases deben de usar sus manos para entrelazar sus muñecas. (creando una plataforma  para  lanzar  a  su  flyer). ', 'Acrobacia'),
(292, 'Flipping Toss & Lanzamiento  mortal', 'Es un lanzamiento ( Toss) en donde la Flyer realiza un movimiento de rotación a través de una posición invertida. ', 'Acrobacia'),
(293, 'Sponge Toss / Lanzamiento de Esponja', 'Una pareja similar a un lanzamiento de Canasta, en la cual la Top/Flyer se lanza desde una posición de “Load­In  / Entrada o Montaje”. La Top/Flyer tiene ambos pies sobre las manos de las bases antes del lanzamiento.  ', 'Acrobacia'),
(294, 'Toss / Lanzamiento', 'Un elemento aéreo donde la(s) base(s) realizan el movimiento de lanzar desde la altura de la cadera a la top para aumentar la altura de la misma. La top realiza un movimiento suelto libre de contacto con todas sus bases. Conectores y/u otro tipo de personas. La Top está libre de contacto con el suelo cuando el lanzamiento es iniciado (ej.  Canastas o Lanzamientos de Esponja “Sponge tosses” ). Nota: T oss to hands (Lanzamiento hacia las manos), toss to Extensión (Lanzamiento extensión y toss chai', 'Acrobacia'),
(295, 'Twisting Tosses / Lanzamientos con giro', 'Los giros son acumulativos. Todos los giros hasta 1­(1/4) son considerados 1 elemento (Skill), si se excede desde 1(1/4) hasta 2(1/4) será considerado como 2 elementos (Skills), ej. un (1/2) giro, X, (1/2) giro, es considerado 2 elementos (skills): 1 giro y  un  elemento  adicional.  ', 'Acrobacia'),
(296, 'Traveling Toss /  Lanzamiento en movimiento', 'Un lanzamiento en el cual intencionalmente se requiere que las bases o receptores se muevan en cierta dirección para recibir a la “flyer”. (Esto  no incluye el ¼ de giro que hacen las  bases  en  lanzamientos  de  kick  full ) ', 'Acrobacia'),
(297, ' X­Out', 'Un elemento de gimnasia o lanzamiento en la cual el atleta ejecuta un mortal mientras se abren los brazos y las piernas ejecutando una “X” durante la  rotación  del  mortal. ', 'Acrobacia'),
(298, 'Pike / Carpado', 'La posición de pike/carpado el cuerpo se dobla hacia delante a la altura de la con las piernas rectas y juntas. Simulando  una “V” cuando es una cuna, y en saltos el cuerpo se dobla hacia el frente simulando tocar los tobillos con las manos.  ', 'Gimnasia'),
(299, 'Jump  /  Salto ', 'Posición aérea que no involucra rotación de cadera­sobre­cabeza creada al usar sus propios pies y la fuerza de la parte inferior del cuerpo para impulsarse de la superficie  de  presentación. ', 'Gimnasia'),
(300, 'Jump Skill / Elemento o habilidad de Salto', 'Un elemento que involucra un cambio en la posición del cuerpo durante el salto. Ej. Toe touch (Japonés),  pike,  etc… ', 'Gimnasia'),
(301, 'Jump Turn / Salto con Giro', 'Cualquier giro que se agregue a un salto. Un “Salto directo” con un giro no hace que el salto sea un “elemento de \nsalto”  o  “Jump  Skill”.  ', 'Gimnasia'),
(302, 'Rebound  /  Rebote', 'Una  posición aérea  que  no  involucra  rotación  de cadera­sobre­cabeza  creada  al  usar  el mismo  pie  del  atleta  y  fuerza  de  la  parte inferior  del  cuerpo  para  rebotar  del suelo  desde  una  acrobacia  en  gimnasia. También  conocido  como  “Pique” (punch). ', 'Gimnasia'),
(303, 'Jump / Salto Ruso', 'Salto aereo en el cual se separan la piernas hasta ejecutar un espagath suspendido, los brazos deben tener una posición en V completamente extendidos y la esplada debe estar completamente recta.', 'Gimnasia'),
(306, 'Arepa quemada', 'Arepa quemada\r\nchicharron cocido\r\ntu equipo es un podrido\r\ny el mio le ha vencido', 'Animacion'),
(307, 'Y Salta y Salta', 'Y Salta y Salta\r\nEl \"B\" vale Callampa\r\nEl \"A\" Sale Puntero\r\nEl \"B\" al Basurero!', 'Animacion'),
(308, 'Con a con a con alegría', 'Con a con a con alegría\r\nCon e con e con entusiasmo\r\nCon i con i con ilusiones\r\nCon o con o con optimismo\r\nCon u con u con un equipo que sera el campeón\r\ny a quien no le guste\r\nque chi que chi que chikitibum a la bimbomba\r\na la vio a la bao a la bimbomba\r\nmi-equipo mi-equipo ra ra ra.', 'Animacion'),
(309, 'Leche la la leche alpura', 'Leche la la leche alpura\r\nla otra porra a la basura.', ''),
(310, 'Con a con a con alegría', 'Con a con a con alegría\r\nCon e con e con entusiasmo\r\nCon i con i con ilusiones\r\nCon o con o con optimismo\r\nCon u con u con un equipo que sera el campeón\r\ny a quien no le guste\r\nque chi que chi que chikitibum a la bimbomba\r\na la vio a la bao a la bimbomba\r\nmi-equipo mi-equipo ra ra ra.', 'Animacion'),
(311, 'Leche', 'Leche la la leche alpura\r\nla otra porra a la basura.', 'Animacion'),
(312, 'Equipo', '(equipo) te quiero wooooo\r\n(equipo) te quiero wooooo\r\nDesde que estoy en (equipo) yo he sido muy feliz.', 'Animacion');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_materiales`
--

CREATE TABLE `inventario_materiales` (
  `IdMaterial` int(11) NOT NULL COMMENT 'Identificar la clase material',
  `Nombre_Material` varchar(100) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Descripcion de utilidad del material',
  `Unidades_Disponibles` int(6) NOT NULL COMMENT 'Unidades disponibles de cada Material',
  `Categoria` enum('Seguridad','Ritmica','Estatica','DePiso') COLLATE latin1_spanish_ci NOT NULL COMMENT 'Fecha de salida del material',
  `IdAdministrador` int(11) NOT NULL,
  `Fecha_Ingreso` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla donde se almacena los materiales en general';

--
-- Volcado de datos para la tabla `inventario_materiales`
--

INSERT INTO `inventario_materiales` (`IdMaterial`, `Nombre_Material`, `Unidades_Disponibles`, `Categoria`, `IdAdministrador`, `Fecha_Ingreso`) VALUES
(62, 'Potro Con prueba', 20, 'Estatica', 1, '2020-09-29 18:51:43'),
(63, 'Viga de equilibrio', 4, 'Estatica', 1, '2020-09-29 18:52:18'),
(64, 'Paralelas ', 15, 'Estatica', 20, '2020-09-28 00:00:00'),
(65, 'Colchonetas Mat', 20, 'Seguridad', 20, '2020-09-29 18:53:32'),
(66, 'Protector Muñequera', 200, 'Seguridad', 20, '2020-09-29 18:53:57'),
(68, 'PRUEBA', 50, 'Seguridad', 1, '2020-09-28 00:00:00'),
(73, 'Varilla  Venturelli', 20, 'Ritmica', 15, '2020-09-29 18:57:46'),
(74, 'Tira Peanna 12mtX3mt', 15, 'DePiso', 1, '2020-09-29 18:58:47'),
(75, 'Minitramp', 4, 'DePiso', 1, '2020-09-29 19:00:34'),
(85, 'Salto de caballo', 4, 'DePiso', 1, '2020-09-29 19:00:49'),
(86, 'Cuerda Venturelli', 50, 'Ritmica', 15, '2020-09-29 18:57:27'),
(87, 'Cinta Chacott', 100, 'Ritmica', 155, '2020-09-29 18:57:08'),
(88, 'Pelota Amaya', 20, 'Ritmica', 155, '2020-09-29 18:56:55');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_evaluacion`
--

CREATE TABLE `items_evaluacion` (
  `IdItems` int(11) NOT NULL COMMENT 'identificacion de la clase items de evaluacion',
  `Nombre_Item` varchar(18) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Nombre de la clase items de evaluacion'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla donde se almacenan los registros de items ';

--
-- Volcado de datos para la tabla `items_evaluacion`
--

INSERT INTO `items_evaluacion` (`IdItems`, `Nombre_Item`) VALUES
(1, 'Postura'),
(2, 'Limpieza'),
(3, 'TrabajoAcrobatico'),
(4, 'ExpresionCorporal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nivel`
--

CREATE TABLE `nivel` (
  `IdNivel` int(11) NOT NULL COMMENT 'Identificacion de la Clase Nivel',
  `Nombre_Nivel` varchar(15) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Nombre del nivel'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla donde se almacena los registros de niveles';

--
-- Volcado de datos para la tabla `nivel`
--

INSERT INTO `nivel` (`IdNivel`, `Nombre_Nivel`) VALUES
(1, 'Novato'),
(2, 'Intermedio'),
(3, 'Avanzado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `apellidos` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `Telefono` bigint(20) NOT NULL,
  `sexo` enum('M','F') CHARACTER SET latin1 DEFAULT NULL,
  `Tipo_Documento` enum('CC','TI','CE','') COLLATE latin1_bin NOT NULL,
  `Documento` int(16) NOT NULL,
  `Edad` int(16) NOT NULL,
  `Factor_De_Riezgo` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`codigo`, `nombre`, `apellidos`, `Telefono`, `sexo`, `Tipo_Documento`, `Documento`, `Edad`, `Factor_De_Riezgo`, `fechaNacimiento`, `email`) VALUES
(173, 'David', 'Vargas', 12541254, 'M', 'TI', 452154, 15, 'N/A', '2003-10-27', 'davidsvargas22@gmail.com'),
(175, 'Nicolas', 'Bernal Cruz', 1238564, 'M', 'CC', 5469824, 25, 'N/A', '2003-10-27', 'nbc27bernal@gmail.com'),
(190, 'David Hernando', 'Chaparro', 322569847, 'M', 'CC', 1236548, 25, 'N/A', '1986-06-22', 'david11chaparro07@gmail.com'),
(193, 'Johan Alexander', 'Farfan Sierra', 322711889, 'M', 'CC', 1073598241, 36, 'N/A', '1986-06-22', 'johanfarfan25@gmail.com'),
(209, 'Prueba3', 'Prueba3', 1213231, 'M', 'CC', 123512, 32, 'Prueba3', '1986-06-21', 'prueba3@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan_de_entrenamiento`
--

CREATE TABLE `plan_de_entrenamiento` (
  `IdPlan` int(11) NOT NULL COMMENT 'Identificador de plan de entrenamiento',
  `IdDeportista` int(11) NOT NULL COMMENT 'identificador del usuario entrenador',
  `IdNivel` int(11) NOT NULL COMMENT 'Identificador de nivel',
  `Fecha_Creacion` datetime DEFAULT current_timestamp() COMMENT 'Fecha de la creacion del plan de entrenamiento',
  `Fecha_Inicio` datetime DEFAULT NULL COMMENT 'Tiempo en que iniciar el tiempo de formación ',
  `Fecha_Final` datetime DEFAULT NULL COMMENT 'Finalización del tiempo de formación',
  `Comprobante_De_Pago` enum('Realizado','No_Realizado','','') COLLATE latin1_spanish_ci DEFAULT NULL COMMENT 'registro donde se evidencia si el pago de la mensualidad es realizado',
  `Estado` enum('Activo','Inactivo','','') COLLATE latin1_spanish_ci NOT NULL COMMENT 'Se muestra el estado actual del Deportista "Activo - Inactivo"',
  `IdEjercicio_1` int(11) DEFAULT NULL COMMENT 'Identificador de la categoria ejercicios animacion',
  `IdEjercicio_2` int(11) DEFAULT NULL COMMENT 'Identificador de la categoria de ejericcios baile',
  `IdEjercicio_3` int(11) DEFAULT NULL COMMENT 'identificador de la categoria ejercicios elementos introductirios',
  `IdEjercicio_4` int(11) DEFAULT NULL COMMENT 'identificador de la categoria ejercicios Gimnasia',
  `IdEntrenador` int(11) DEFAULT NULL COMMENT 'identificador del usuario entrenador',
  `Recomendaciones` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

CREATE TABLE `prestamo` (
  `IdPrestamo` int(11) NOT NULL COMMENT 'Identificador del prestamo material',
  `IdMaterial` int(11) NOT NULL COMMENT 'Identificador del material',
  `Fecha_Prestamo` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha en que se presta el Material',
  `Fecha_Devolucion` datetime DEFAULT NULL COMMENT 'Fecha en que se regresa el material',
  `IdEntrenador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `prestamo`
--

INSERT INTO `prestamo` (`IdPrestamo`, `IdMaterial`, `Fecha_Prestamo`, `Fecha_Devolucion`, `IdEntrenador`) VALUES
(112, 64, '2020-12-10 19:31:05', '2020-12-10 00:00:00', 175),
(113, 65, '2021-01-29 09:16:13', NULL, 60),
(115, 65, '2021-01-29 09:30:15', NULL, 175);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultados1`
--

CREATE TABLE `resultados1` (
  `IdResultado` int(11) NOT NULL,
  `IdSemana1` int(11) NOT NULL,
  `IdSemana2` int(11) NOT NULL,
  `IdSemana3` int(11) NOT NULL,
  `IdSemana4` int(11) NOT NULL,
  `NotaFinal` int(11) NOT NULL,
  `Resultado` enum('Aprobado','Reprobado','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `semana_1`
--

CREATE TABLE `semana_1` (
  `IdSemana1` int(11) NOT NULL,
  `IdPlan` int(11) NOT NULL,
  `IdItems` int(11) NOT NULL,
  `Calificacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `semana_2`
--

CREATE TABLE `semana_2` (
  `IdSemana2` int(11) NOT NULL,
  `IdPlan` int(11) NOT NULL,
  `IdItems` int(11) NOT NULL,
  `Calificacion` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `semana_3`
--

CREATE TABLE `semana_3` (
  `IdSemana3` int(11) NOT NULL,
  `IdPlan` int(11) NOT NULL,
  `IdItems` int(11) NOT NULL,
  `calificacion` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `semana_4`
--

CREATE TABLE `semana_4` (
  `IdSemana4` int(11) NOT NULL,
  `IdPlan` int(11) NOT NULL,
  `IdItems` int(11) NOT NULL,
  `Calificacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `email` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`email`, `password`, `name`) VALUES
('david11chaparro07@gmail.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 'David Chaparro'),
('davidsvargas22@gmail.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 'David Vargas'),
('johanfarfan25@gmail.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 'Johan Farfan'),
('nbc27bernal@gmail.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 'Nicolas Bernal'),
('prueba3@gmail.com', 'dHb5j3F/5d1TWWTyPXYxnCzid/E1FK5A9ckEjG5XGJM=', 'Prueba3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_groups`
--

CREATE TABLE `user_groups` (
  `email` varchar(255) NOT NULL,
  `groupname` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `user_groups`
--

INSERT INTO `user_groups` (`email`, `groupname`) VALUES
('david11chaparro07@gmail.com', 'administrador'),
('davidsvargas22@gmail.com', 'users'),
('johanfarfan25@gmail.com', 'users'),
('nbc27bernal@gmail.com', 'entrenador'),
('prueba3@gmail.com', 'users');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `codigo` int(11) NOT NULL,
  `usuario` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `Tipo` enum('Administrador','Entrenador','Deportista','') DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`codigo`, `usuario`, `Tipo`, `estado`) VALUES
(173, 'David Vargas', 'Deportista', 1),
(175, 'Nicolas Bernal', 'Entrenador', 1),
(190, 'David Caparro', 'Administrador', 1),
(193, 'Johan Farfan', 'Deportista', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ejercicios`
--
ALTER TABLE `ejercicios`
  ADD PRIMARY KEY (`IdEjercicios`);

--
-- Indices de la tabla `inventario_materiales`
--
ALTER TABLE `inventario_materiales`
  ADD PRIMARY KEY (`IdMaterial`),
  ADD KEY `IdAdministrador_2` (`IdAdministrador`);

--
-- Indices de la tabla `items_evaluacion`
--
ALTER TABLE `items_evaluacion`
  ADD PRIMARY KEY (`IdItems`);

--
-- Indices de la tabla `nivel`
--
ALTER TABLE `nivel`
  ADD PRIMARY KEY (`IdNivel`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `codigo_UNIQUE` (`codigo`) USING BTREE,
  ADD UNIQUE KEY `Documento` (`Documento`),
  ADD KEY `email` (`email`);

--
-- Indices de la tabla `plan_de_entrenamiento`
--
ALTER TABLE `plan_de_entrenamiento`
  ADD PRIMARY KEY (`IdPlan`),
  ADD KEY `IdEntrenador` (`IdEntrenador`),
  ADD KEY `IdDeportista` (`IdDeportista`),
  ADD KEY `IdNivel` (`IdNivel`),
  ADD KEY `IdEjercicio_1` (`IdEjercicio_1`),
  ADD KEY `IdEjercicio_2` (`IdEjercicio_2`),
  ADD KEY `IdEjercicio_4` (`IdEjercicio_4`),
  ADD KEY `IdEjercicio_3` (`IdEjercicio_3`);

--
-- Indices de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD PRIMARY KEY (`IdPrestamo`),
  ADD KEY `IdEntrenador` (`IdEntrenador`),
  ADD KEY `IdMaterial` (`IdMaterial`);

--
-- Indices de la tabla `resultados1`
--
ALTER TABLE `resultados1`
  ADD PRIMARY KEY (`IdResultado`),
  ADD KEY `IdSemana1` (`IdSemana1`),
  ADD KEY `IdSemana2` (`IdSemana2`),
  ADD KEY `IdSemana3` (`IdSemana3`),
  ADD KEY `IdSemana4` (`IdSemana4`);

--
-- Indices de la tabla `semana_1`
--
ALTER TABLE `semana_1`
  ADD PRIMARY KEY (`IdSemana1`),
  ADD KEY `IdPlan` (`IdPlan`),
  ADD KEY `IdItems` (`IdItems`);

--
-- Indices de la tabla `semana_2`
--
ALTER TABLE `semana_2`
  ADD PRIMARY KEY (`IdSemana2`),
  ADD KEY `IdItems` (`IdItems`),
  ADD KEY `IdPlan` (`IdPlan`) USING BTREE;

--
-- Indices de la tabla `semana_3`
--
ALTER TABLE `semana_3`
  ADD PRIMARY KEY (`IdSemana3`),
  ADD KEY `IdPlan` (`IdPlan`),
  ADD KEY `IdItems` (`IdItems`);

--
-- Indices de la tabla `semana_4`
--
ALTER TABLE `semana_4`
  ADD PRIMARY KEY (`IdSemana4`),
  ADD KEY `IdPlan` (`IdPlan`),
  ADD KEY `IdItems` (`IdItems`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`);

--
-- Indices de la tabla `user_groups`
--
ALTER TABLE `user_groups`
  ADD PRIMARY KEY (`email`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ejercicios`
--
ALTER TABLE `ejercicios`
  MODIFY `IdEjercicios` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificacion del ejercicios', AUTO_INCREMENT=630;

--
-- AUTO_INCREMENT de la tabla `inventario_materiales`
--
ALTER TABLE `inventario_materiales`
  MODIFY `IdMaterial` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificar la clase material', AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT de la tabla `items_evaluacion`
--
ALTER TABLE `items_evaluacion`
  MODIFY `IdItems` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identificacion de la clase items de evaluacion', AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `nivel`
--
ALTER TABLE `nivel`
  MODIFY `IdNivel` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificacion de la Clase Nivel', AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=210;

--
-- AUTO_INCREMENT de la tabla `plan_de_entrenamiento`
--
ALTER TABLE `plan_de_entrenamiento`
  MODIFY `IdPlan` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador de plan de entrenamiento', AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  MODIFY `IdPrestamo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador del prestamo material', AUTO_INCREMENT=118;

--
-- AUTO_INCREMENT de la tabla `resultados1`
--
ALTER TABLE `resultados1`
  MODIFY `IdResultado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `semana_1`
--
ALTER TABLE `semana_1`
  MODIFY `IdSemana1` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;

--
-- AUTO_INCREMENT de la tabla `semana_2`
--
ALTER TABLE `semana_2`
  MODIFY `IdSemana2` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;

--
-- AUTO_INCREMENT de la tabla `semana_3`
--
ALTER TABLE `semana_3`
  MODIFY `IdSemana3` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;

--
-- AUTO_INCREMENT de la tabla `semana_4`
--
ALTER TABLE `semana_4`
  MODIFY `IdSemana4` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`email`) REFERENCES `users` (`email`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `plan_de_entrenamiento`
--
ALTER TABLE `plan_de_entrenamiento`
  ADD CONSTRAINT `plan_de_entrenamiento_ibfk_10` FOREIGN KEY (`IdEntrenador`) REFERENCES `usuario` (`codigo`),
  ADD CONSTRAINT `plan_de_entrenamiento_ibfk_4` FOREIGN KEY (`IdNivel`) REFERENCES `nivel` (`IdNivel`),
  ADD CONSTRAINT `plan_de_entrenamiento_ibfk_5` FOREIGN KEY (`IdEjercicio_1`) REFERENCES `ejercicios` (`IdEjercicios`),
  ADD CONSTRAINT `plan_de_entrenamiento_ibfk_6` FOREIGN KEY (`IdEjercicio_2`) REFERENCES `ejercicios` (`IdEjercicios`),
  ADD CONSTRAINT `plan_de_entrenamiento_ibfk_7` FOREIGN KEY (`IdEjercicio_3`) REFERENCES `ejercicios` (`IdEjercicios`),
  ADD CONSTRAINT `plan_de_entrenamiento_ibfk_8` FOREIGN KEY (`IdEjercicio_4`) REFERENCES `ejercicios` (`IdEjercicios`),
  ADD CONSTRAINT `plan_de_entrenamiento_ibfk_9` FOREIGN KEY (`IdDeportista`) REFERENCES `usuario` (`codigo`);

--
-- Filtros para la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`IdMaterial`) REFERENCES `inventario_materiales` (`IdMaterial`);

--
-- Filtros para la tabla `resultados1`
--
ALTER TABLE `resultados1`
  ADD CONSTRAINT `resultados1_ibfk_1` FOREIGN KEY (`IdSemana1`) REFERENCES `semana_1` (`IdSemana1`),
  ADD CONSTRAINT `resultados1_ibfk_2` FOREIGN KEY (`IdSemana2`) REFERENCES `semana_2` (`IdSemana2`),
  ADD CONSTRAINT `resultados1_ibfk_3` FOREIGN KEY (`IdSemana3`) REFERENCES `semana_3` (`IdSemana3`),
  ADD CONSTRAINT `resultados1_ibfk_4` FOREIGN KEY (`IdSemana4`) REFERENCES `semana_4` (`IdSemana4`);

--
-- Filtros para la tabla `semana_1`
--
ALTER TABLE `semana_1`
  ADD CONSTRAINT `semana_1_ibfk_1` FOREIGN KEY (`IdPlan`) REFERENCES `plan_de_entrenamiento` (`IdPlan`),
  ADD CONSTRAINT `semana_1_ibfk_2` FOREIGN KEY (`IdItems`) REFERENCES `items_evaluacion` (`IdItems`);

--
-- Filtros para la tabla `semana_2`
--
ALTER TABLE `semana_2`
  ADD CONSTRAINT `semana_2_ibfk_1` FOREIGN KEY (`IdPlan`) REFERENCES `plan_de_entrenamiento` (`IdPlan`),
  ADD CONSTRAINT `semana_2_ibfk_2` FOREIGN KEY (`IdItems`) REFERENCES `items_evaluacion` (`IdItems`);

--
-- Filtros para la tabla `semana_3`
--
ALTER TABLE `semana_3`
  ADD CONSTRAINT `semana_3_ibfk_1` FOREIGN KEY (`IdPlan`) REFERENCES `plan_de_entrenamiento` (`IdPlan`),
  ADD CONSTRAINT `semana_3_ibfk_2` FOREIGN KEY (`IdItems`) REFERENCES `items_evaluacion` (`IdItems`);

--
-- Filtros para la tabla `semana_4`
--
ALTER TABLE `semana_4`
  ADD CONSTRAINT `semana_4_ibfk_1` FOREIGN KEY (`IdPlan`) REFERENCES `plan_de_entrenamiento` (`IdPlan`),
  ADD CONSTRAINT `semana_4_ibfk_2` FOREIGN KEY (`IdItems`) REFERENCES `items_evaluacion` (`IdItems`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `codigo` FOREIGN KEY (`codigo`) REFERENCES `persona` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
