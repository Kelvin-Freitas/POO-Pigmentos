Sistema de Gerenciamento de Vendas de Pigmentos
================

Trata-se de um sistema de controle de vendas de pigmentos. A loja mantém um estoque de
pigmentos. A representação das cores dos pigmentos pode ser feita de diversas formas. Em
particular, pigmentos podem ser representados através de dois padrões:
  * RGB **(red, green, blue)**
  * CMYK **(cyan, magenta, yellow, key)**
  
A empresa atualmente opera com várias linhas de pigmentos, que se distribuem nos dois
padrões acima. Assim, a base da empresa armazena estas duas classes de pigmentos. De
cada pigmento precisamos armazenar o estoque disponível, o preço do litro, o identificador
do pigmento e o seu nome de fantasia. Por exemplo: <ID=“Alpha42B”, NOME=“Especiaria
Antiga”, ESTOQUE=“32 Litros”, PREÇO=“7,50”>

[...]

O sistema consiste em fornecer uma entrada, onde o usuário irá receber um valor em
notação HTML (RRGGBB em hexa, por exemplo: FF0000 para vermelho puro) da cor e a
quantidade desejada. Em seguida, o sistema irá buscar todos os pigmentos com a
quantidade disponível maior ou igual a quantidade informada como desejada. Destes
pigmentos, o sistema irá buscar o mais parecido com a cor pedida (cálculo de distância e
retorna a cor com a menor distância) e apresentará o valor da venda (preço do pigmento
multiplicado pela quantidade desejada). Caso a venda seja confirmada, a quantidade deve
ser abatida do estoque da cor vendida. O cálculo da distância entre cores RGB é dado 
pela **distância euclidiana** entre as cores.

O cálculo da distância entre uma cor CMYK e uma cor RGB deve ser feita com a conversão
prévia de CMYK para RGB e só então se realizando o cálculo da distância euclidiana entre
as cores.

## Agradecimentos

A Kelvin e Bruno, da equipe do trabalho.
