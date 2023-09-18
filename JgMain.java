import java.util.*;
import java.util.Scanner;
import java.util.Random;
/** */

  public class JgMain {
    private static void mover(Pilha origem, Pilha destino) {
    int valorMovido = origem.pop();
    destino.push(valorMovido);
  }
    //
 static void solucaoAutomatica(int n, Pilha origem, Pilha destino, 
 Pilha auxiliar) {
    if (n > 0) {
      solucaoAutomatica(n - 1, origem, auxiliar, destino);
      mover(origem, destino);
      //imprimirEstadoPilhas();
      solucaoAutomatica(n - 1, auxiliar, destino, origem);
      System.out.println("Pilha 1: " + origem.getValores());
      System.out.println("Pilha 2: " + auxiliar.getValores());
      System.out.println("Pilha 3: " + destino.getValores());      
    }
  }
    
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Informe o tamanho desejado para as pilhas: ");
    int tamanhoPs = scanner.nextInt();
    int i;
    
    Pilha pilhaOrigem = new Pilha(tamanhoPs);
    Pilha pilhaAux = new Pilha(tamanhoPs);
    Pilha pilhaDestino = new Pilha(tamanhoPs);

    // Preenchendo Pilha1 com nº aleatorios
    // Lembrar +1 para nº aleatorio
    Random random = new Random();
    for (i = 0; i < tamanhoPs; i++) {
      int numRandomico = random.nextInt(100) + 1;
      pilhaOrigem.push(numRandomico);
      //System.out.print(numRandomico + " ");
    }

    boolean ordenado = false;
    int contJogadas = 0;

    //Loop principal do jogo
    while (!ordenado) {
      
      // Imprimir as três pilhas
      System.out.println("Pilha 1: " + pilhaOrigem.getValores());
      System.out.println("Pilha 2: " + pilhaAux.getValores());
      System.out.println("Pilha 3: " + pilhaDestino.getValores());

            //menu de opções
      System.out.println("----------------------");
      System.out.println("Escolha uma opção:");
      System.out.println("1 - Movimentar");
      System.out.println("2 - Solução automática");
      System.out.println("0 - Sair");
      System.out.println("----------------------");

      int escolha = scanner.nextInt();

// Implemente a lógica para mover números entre as pilhas
switch (escolha) {
    case 1:
        System.out.println("Informe de qual pilha deseja mover o nº (1, 2 ou 3)");
        int origem = scanner.nextInt();
        System.out.println("Informe para qual pilha deseja mover o nº (1, 2 ou 3)");
        int destino = scanner.nextInt();

        if (!pilhaOrigem.estaVazio()) {
            int valorMov = 0; 
            if (origem == 1) {
                valorMov = pilhaOrigem.pop();
            } else if (origem == 2) {
                valorMov = pilhaAux.pop();
            } else if (origem == 3) {
                valorMov = pilhaDestino.pop();
            } else {
                System.out.println("Origem inválida. Pilha não existe.");
                break;
            }

            if (destino == 1) {
                pilhaOrigem.push(valorMov);
            } else if (destino == 2) {
                pilhaAux.push(valorMov);
            } else if (destino == 3) {
                pilhaDestino.push(valorMov);
            } else {
                System.out.println("Movimento inválido");
            }

            contJogadas++;
        } else {
            System.out.println("Pilha de origem vazia.");
        }

        // Verificar ordenação/se ordenado, o jogo encerra
        if (pilhaDestino.estaCheia() && pilhaDestino.verificarOrdenacao()) {
            ordenado = true;
            System.out.println("Jogo Finalizado | Total de jogada: " + contJogadas + " jogadas.");
        }

        break;
    case 2:
        // Implemente a solução automática (ordenar a Pilha 1)
        // Atualize o contador de jogadas
        System.out.println("Solução automática iniciada...");
        solucaoAutomatica(tamanhoPs, pilhaOrigem, pilhaDestino, pilhaAux);
        ordenado = true;
        System.out.println("Solução automática concluída | Jogo encerrado ");
        break;
    case 0:
        // Encerre o jogo
        System.out.println("Jogo encerrado.");
        ordenado = true;
        break;
    default:
        System.out.println("Opção inválida. Tente novamente.");
}
class Pilha {
  private No topo;
  private int tamanhoMaximo;

  //Construtor da Pilha
  public Pilha(int tamanhoPs) {
    topo = null; // inicia com a pilha vazia
    this.tamanhoMaximo = tamanhoPs;
  }

  //metodo para verificar se a pilha está vazia
  public boolean estaVazio() {
    return topo == null;
  }
  
    public boolean estaCheia() {
    return getValores().size() >= tamanhoMaximo;
  }

  //inserir valor na pilha (push)
  public void push(int valor) {
    if (estaCheia()) {
      System.out.println("A pilha está cheia.");
      return;
    }
    No novoNo = new No(valor); //criar novo no
    novoNo.proximo = topo; //  novo nº, o antigo é o proximo no
    topo = novoNo; //novo nó vra topo
  }

  //remover valor da pilha (pop)
  public int pop() {
    if (estaVazio()) {
      System.out.println("A pilha está vazia.");
      return -1;
    }

    int valor = topo.valor; //valor do topo
    topo = topo.proximo; //atualizar o topo para o próximo nó
    return valor; //retorna o valor removido
  }

  public List<Integer> getValores() {
    List<Integer> valores = new ArrayList<>();
    No current = topo;
    while (current != null) {
        valores.add(current.valor);
        current = current.proximo;
    }
    return valores;
  }
  // Dentro da classe Pilha
public boolean verificarOrdenacao() {
  No current = topo;
  int valorAnterior = Integer.MIN_VALUE;

  while (current != null) {
    if (current.valor < valorAnterior) {
      return false; // A pilha não está ordenada
    }
    valorAnterior = current.valor;
    current = current.proximo;
  }

  return true; // A pilha está ordenada
}

  //represnetacao do nó na lista encadeada
  private class No {
    int valor;
    No proximo;

    public No(int valor) {
      this.valor = valor;
      this.proximo = null;
    }
  }
  }
    }}}
