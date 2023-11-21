package exercisesP2;

import java.util.Comparator;
import java.util.List;

import us.lsi.common.List2;

public class Exercise2 {
	
	public static <E extends Comparable<? super E>> void mergeSort(List<E> lista, Integer umbral){
		Comparator<? super E> ord = Comparator.naturalOrder();
		List<E> ls = List2.ofCollection(lista);
		mgSort(lista,0,lista.size(),ord,ls,umbral);	
	}

	private static <E> void mgSort(List<E> lista, int i, int j, Comparator<? super E> ord, List<E> ls, Integer umbral){
		if(j-i <= umbral){
			ordenaBase(lista, i, j, ord);
		}
		else {
			int k = (j+i)/2;
			mgSort(lista,i,k,ord,ls,umbral);
			mgSort(lista,k,j,ord,ls,umbral);
			mezcla(lista,i,k,lista,k,j,ls,i,j,ord);
			copia(lista,i,j,ls);
		}
	}

	private static <E> void mezcla(List<E> l1, int i1, int j1, List<E> l2, int i2, int j2,List<E> l3, int i3, int j3, Comparator<? super E> ord){
		int k1= i1; //i in list il
		int k2= i2; //k
		int k3= i3; //i in ils
		while(k3<j3){ //each element of the list
			if(k1<j1 && k2<j2){ //il < k & k < j
				if(ord.compare(l1.get(k1), l2.get(k2))<=0){ //l(il) < l(k)
					l3.set(k3, l1.get(k1)); //sets ls(ils) to l(il)
					k1++; //l(il) was set, il+1
					k3++;
				}else{ //l(il) >= l(k)
					l3.set(k3, l2.get(k2)); //sets ls(ils) to l(k)
					k2++; //k was set, k+1
					k3++; //l(ils) was set, ils + 1
				}
			}else if(k2==j2){ //k is the end of the list (no more elem)
				l3.set(k3, l1.get(k1)); //set ls(ils) to l(il) (unique left)
				k1++;
				k3++;
			}else{ //il = k
				l3.set(k3, l2.get(k2)); //set ls(ils) to l(k) 
				k2++;
				k3++;
			}
		}
	}
	
	private static <E> void copia(List<E> lista, int i, int j, List<E> ls){
		for(int k = i; k<j; k++){
			lista.set(k, ls.get(k));
		}
	}

	public static <T> void ordenaBase(List<T> lista, Integer inf, Integer sup, Comparator<? super T> ord) {		
		for (int i = inf; i < sup; i++) {
		      for(int j = i+1; j < sup; j++){
		    	  if(ord.compare(lista.get(i),lista.get(j))>0){
		    		  List2.intercambia(lista, i, j);
		    	  }
		      }
		}
	}

}
