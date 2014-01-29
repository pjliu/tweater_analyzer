import java.io.*;
import java.util.*;

 
public class TestSort {

    public TestSort ()
    {
    }

    private void perform() {

      	String  line = null;
      	try{
		BufferedReader br = new BufferedReader(new FileReader("c:/temp/java/usernames_keywords.txt"));
		Map map = new HashMap();
         	while ((line = br.readLine()) != null) {
			String[] tmp = line.split("\t");
			if (tmp.length == 2) {
				String name = tmp[0];
				String kw = tmp[1];
            			//System.out.println(name+":"+kw);
				Map nameCount = null;
				if (map.containsKey(kw)) {
					nameCount = (Map)map.get(kw);
				} else {
					nameCount = new HashMap();
					map.put(kw, nameCount);
				}
				int cnt = 0;
				if (nameCount.containsKey(name)) {
					cnt = ((Integer)nameCount.get(name)).intValue();
				}
				nameCount.put(name, new Integer(cnt+1)); 
			} else {
				System.out.println("cannot process line:"+line);
			}			
         	}

		Set set = map.entrySet(); 
		Iterator i = set.iterator(); 
		while(i.hasNext()) { 
			Map.Entry me = (Map.Entry)i.next();
			String kw = (String)me.getKey();
			Map nameCount = (Map)me.getValue();
			Map sortedNameCount = sortByComparator(nameCount);
			System.out.println(kw + ":"); 
			System.out.println(sortedNameCount);
			System.out.println();	 
		}     
      	} catch(Exception e){
		e.printStackTrace();
      	}
    }

    private static Map sortByComparator(Map unsortMap) {
 
	List list = new LinkedList(unsortMap.entrySet()); 
	// sort list based on comparator
	Collections.sort(list, new Comparator() {
		public int compare(Object o1, Object o2) {
			return -1*((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
		}
	});
 
	// put sorted list into map again
        //LinkedHashMap make sure order in which keys were inserted
	Map sortedMap = new LinkedHashMap();
	int i = 0;
	int max = 10;
	for (Iterator it = list.iterator(); it.hasNext();) {
		if (i<max) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
			i++;
		} else {
			break;
		}
	}
	return sortedMap;
    }

    public static void main(String[] args) {
	TestSort test = new TestSort();
	test.perform();       	
   }
}



