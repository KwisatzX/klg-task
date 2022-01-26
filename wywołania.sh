g=`tput setaf 2`
r=`tput sgr0`
printf ${g}"Rezerwacje dla osoby (przez nazwe najemcy):\n"
echo "curl -i http://localhost:8080/api/reservations?renter=Jan%20Kowalski"${r}
curl -i http://localhost:8080/api/reservations?renter=Jan%20Kowalski
printf ${g}"\nRezerwacje dla osoby (przez id osoby):\n"
printf "curl -i http://localhost:8080/api/person/3/reservations\n"${r}
curl -i http://localhost:8080/api/person/3/reservations
printf ${g}"\nRezerwacje dla własności (metoda 1):\n"
printf "curl -i http://localhost:8080/api/reservations?property=1\n"${r}
curl -i http://localhost:8080/api/reservations?property=1
printf ${g}"\nRezerwacje dla własności (metoda 2):\n"
printf "curl -i http://localhost:8080/api/property/2/reservations\n"${r}
curl -i http://localhost:8080/api/property/2/reservations
printf ${g}"\nDodanie poprawnej rezerwacji:\n"
printf "curl -i -H \"Content-Type: application/json\" --data @goodreservation.json http://localhost:8080/api/reservations/add\n"${r}
curl -i -H "Content-Type: application/json" --data @goodreservation.json http://localhost:8080/api/reservations/add
printf ${g}"\nRezerwacja dodana do własności:\n"${r}
curl -i http://localhost:8080/api/reservations?property=2
printf ${g}"\nDodanie niepoprawnej rezerwacji:\n"
printf "curl -i -H \"Content-Type: application/json\" --data @badreservation.json http://localhost:8080/api/reservations/add\n"${r}
curl -i -H "Content-Type: application/json" --data @badreservation.json http://localhost:8080/api/reservations/add
printf ${g}"\nZmiana rezerwacji (przedłużenie o rok):\n"
printf "curl -i -X PUT -H \"Content-Type: application/json\" --data @editedreservation.json http://localhost:8080/api/reservations/1/edit\n"${r}
curl -i -X PUT -H "Content-Type: application/json" --data @editedreservation.json http://localhost:8080/api/reservations/1/edit
printf ${g}"\nZmieniona rezerwacja:\n"${r}
curl -i http://localhost:8080/api/reservations/1