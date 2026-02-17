# Simulador de Fútbol en Java

## Qué es esto
Un simulador de partidos de fútbol hecho en Java.
Aplica herencia, polimorfismo, colecciones y gestión de ficheros.

## Cómo funciona:

### Estructura de clases
- **Futbolista**: clase padre abstracta. Tiene nombre, dorsal, equipo, pases, goles, paradas, tiros. 
  El método pasar() tiene un 85% de éxito y suma un pase cuando acierta.
- **Portero, Defensa, Medio, Delantero**: heredan de Futbolista. Cada una con su toString y 
  las interfaces que corresponden (PuedeParar para portero, PuedeTirar para el resto).
- **Equipo**: contiene ArrayList con 7 jugadores (1 portero, 2 defensas, 2 medios, 2 delanteros) 
  y lleva las estadísticas totales.
- **Partido**: aquí está la lógica. Simula 90 minutos con 5 acciones por minuto.
- **Main**: crea los equipos y lanza la simulación.

### Reglas del partido
- La posesión inicial se decide aleatoriamente.
- Cada acción es un intento de pase (85% de éxito).
- Si el pase falla, cambia la posesión.
- Cuando un equipo completa 10 pases seguidos, tira a puerta.
- El tiro tiene 30% de probabilidad de gol y 70% de parada.
- Si el portero para, se queda el balón y su equipo empieza a pasar.
- Si hay gol, el equipo que lo recibe saca de centro.
- Solo se registran en el archivo los goles y las paradas (con minuto y protagonistas).

### Archivos que genera
Cada partido crea un archivo partido1.txt, partido2.txt... (si ya existe, lo sobrescribe).
Dentro viene la narración de goles y paradas, y al final las estadísticas de los dos equipos.

## Cómo ejecutarlo
Compilas todo y ejecutas el Main. Por defecto simula 3 partidos con el Barça y el Madrid 
(plantilla 2025). Si quieres cambiar los equipos o el número de partidos, se toca en el main.

## Lo que he aprendido
- Herencia bien usada evita repetir código.
- El polimorfismo permite tratar a todos los jugadores como Futbolista pero que cada uno 
  se comporte diferente cuando toca.
- Las colecciones (ArrayList) son cómodas para guardar grupos de objetos.
- Escribir en ficheros con BufferedWriter es sencillo y da control.
- Una buena estructura desde el principio ahorra dolores de cabeza después.
