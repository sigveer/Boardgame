# Boardgame

## Beskrivelse
Dette er et brettspill-prosjekt utviklet i Java. Prosjektet gir brukere muligheten til å spille forskjellige brettspill gjennom et programmatisk grensesnitt.

## Innholdsfortegnelse
- [Installasjon](#installasjon)
- [Bruk](#bruk)
- [Funksjoner](#funksjoner)
- [Teknologier](#teknologier)
- [Prosjektstruktur](#prosjektstruktur)
- [Bidrag](#bidrag)

## Installasjon
For å installere og kjøre dette prosjektet lokalt, følg disse trinnene:

1. Klone prosjektet
   ```bash
   git clone https://github.com/sigveer/Boardgame.git
   ```

2. Naviger til prosjektmappen
   ```bash
   cd Boardgame
   ```

3. Kompiler prosjektet (avhengig av din byggprosess)
   ```bash
   mvn clean install
   ```

4. Kjør applikasjonen
   ```bash
   mvn javafx:run
   ```

   eller

   ```bash
   java -jar target/boardgame.jar
   ```

## Bruk
Beskrivelse av hvordan man bruker applikasjonen:

1. Start programmet ved å følge installasjonsanvisningene.

2. Velg spillere og ønsket brettspill fra hovedmenyen.

![image](https://github.com/user-attachments/assets/d1aa05e0-bee1-4dee-9d05-d0d3924866e1)

   
3.Kast terning og spill i vei!

![image](https://github.com/user-attachments/assets/e13e0c81-e5b3-47a4-a1dc-6653c1a917b6)


## Funksjoner
- Støtte for flere forskjellige brettspill
- Laste opp/ned egne CSV filer for spillere
- Laste opp egendefinert JSON brett til stige spill
- Enkelt brukergrensesnitt for spillinteraksjon
- Muligheter for både enkeltspiller og flerspiller

## Teknologier
- Java og JavaFX
- Maven

## Prosjektstruktur

![Skjermbilde 2025-05-22 183602](https://github.com/user-attachments/assets/780660da-dcf3-4ce6-ba7a-813bfb2e5cad)
![Skjermbilde 2025-05-22 183611](https://github.com/user-attachments/assets/c1a0ff65-d77d-4d69-b4ec-ed912c212b7c)


## Bidrag
Hvis du ønsker å bidra til prosjektet, følg disse trinnene:

1. Fork repositoriet
2. Opprett en ny branch (`git checkout -b feature/featurename`)
3. Gjør dine endringer
4. Commit endringene (`git commit -m 'Legg til en ny funksjon'`)
5. Push til branchen (`git push origin feature/featurename`)
6. Åpne en Pull Request
