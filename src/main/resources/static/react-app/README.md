Aby vám appka šlapala, nainstalujte si npm balíčky v rámci projektu:
- Bootstrap - npm i bootstrap@5.3.2 - a pak mrkněte na import v src/index.js, přidejte si jej takto, jinak vám bs nepojede
- axios - npm i axios

Deployment
- deployment je na stránce https://veetektest.g6.cz/projects/weather-app/index.html
- je nastaveno, aby se co dvě minuty aktualizovala data z API - projeví se i na výpisu dané lokality, pokud se mezitím data změnila (interval si klidně změňte na delší)
- čas poslední aktualizace je vypsán pod výběrem lokality
- poznámka ke kódu - je to pouze ukázkové řešení, jak by mohl FE k appce vypadat, dozajista by se dalo udělat spanilejší a propracovanější řešení, leč smyslem bylo ukázat, jakým směrem je možno se vydat v konkrétním případě... zde s použitím axios balíčku pro získání dat z APIny
