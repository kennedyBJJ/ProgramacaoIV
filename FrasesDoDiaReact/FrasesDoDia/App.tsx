//importe necessário para usar a biblioteca do react e estados
import React, {useState} from 'react';

//Importes dos componentes necessários para criar um código react
import {View, Text, TouchableOpacity, StyleSheet} from 'react-native';

const frases = [
  'Não ganhe o mundo e perca sua alma; sabedoria é melhor que prata e ouro.',
  'Não viva para que a sua presença seja notada, mas para que a sua falta seja sentida.',
  'É preciso que as diferenças não diminuam a amizade e que a amizade não diminua as diferenças.',
  'Nunca deixe ninguém te dizer que não pode fazer alguma coisa. Se você tem um sonho tem que correr atrás dele. As pessoas não conseguem vencer e dizem que você também não vai vencer. Se você quer uma coisa corre atrás.',
  'Para ter sabedoria, é preciso primeiro pagar o seu preço. Use tudo o que você tem para adquirir o entendimento.',
  'Nós somos apenas simples pessoas movidas pela vingança em nome da justiça. Mas se a vingança é chamada de justiça, então dessa justiça irá nascer ainda mais vingança... E então se torna uma corrente de ódio. Viver com isso, ciente do passado, predizando o futuro, isso que significa conhecer a história. Não podemos evitar, mas sim compreender, que as pessoas nunca entenderão uma as outras.',
  'Acima de tudo, guarde o seu coração, pois dele depende toda a sua vida.',
  'A resposta calma desvia a fúria, mas a palavra ríspida desperta a ira.',
  'A bênção do Senhor é que enriquece; e não traz consigo dores.',
  'Não repreenda o zombador, caso contrário ele o odiará; repreenda o sábio, e ele o amará.',
  'Clame por inteligência e peça entendimento. Pois o Senhor concede sabedoria; de sua boca vêm conhecimento e entendimento.',
  'Eu faço da dificuldade a minha motivação. A volta por cima vem na continuação.',
  'Pra quem tem pensamento forte, o impossível é só questão de opinião.',
  'Quem ousou conquistar e saiu pra lutar, chega mais longe!',
  'A arte maior é o jeito de cada um... vivo pra ser feliz não vivo pra ser comum.',
  'A vida me ensinou a nunca desistir. Nem ganhar, nem perder mas procurar evoluir.',
  'O tempo às vezes é alheio à nossa vontade, mas só o que é bom dura tempo o bastante pra se tornar inesquecível.',
  'Hoje estou feliz porque sonhei com você, e amanhã posso chorar por não poder te ver .',
];

//criando o componente que forma o aplicativo
const FrasesDoDiaAPP = () => {
  let indice = Math.floor(Math.random() * frases.length);
  
  //para mudar um conteudo dinamicamente pode se usar state (estados)
  //a sintaxe de estados é
  //variavel: armazena um valor/dado
  //função: modifica o valor da variavel 
  //[<variavel>, <função>]


  const mudarFrase = () => {
    indice = Math.floor(Math.random() * frases.length);
    setFrase(frases[indice]);
  }

  const [frase, setFrase] = useState(frases[indice]);
  return (
    <View style={styles.container}>
      <Text>{frase}</Text>
      <TouchableOpacity style={styles.button}
        onPress={mudarFrase}
        >
        <Text style={styles.text}> Nova Frase</Text>
      </TouchableOpacity>
    </View>
  );
};

//estilos
const styles = StyleSheet.create(
  {
    container: 
    {
      flex: 1,
      alignContent: 'center',
      justifyContent: 'space-evenly',
      padding: 10,
      
    },
    
    button:
    {
      justifyContent: 'center',
      minHeight: 50,
      backgroundColor: 'green',
      borderRadius: 5,
      marginLeft: '30%',
      marginRight: '30%',
    },

    text:
    {
      color: 'white',
      textAlign: 'center',
      
    }
  }
)

export default FrasesDoDiaAPP;
