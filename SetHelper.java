/*
	Set Helper 1.5
	Jim Halliday
	Finished December 1997
	Converted from applet to standalone application October 2014
	Final Version!
	This program can be used to calculate pitch-class sets using a calculator-like interface.
	The program actually calculates the interval-class vector first, then uses it to find
	the prime form from a list of sets. Z-related sets are taken through one further 
	step to tell them apart. 

*/

import java.awt.*;

import javax.swing.*;

public class SetHelper extends JFrame {
	
	//initialize buttons, fields, etc.
	Button Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Eleven, Reset;
	Panel ButtonPanel, GridPanel;
	TextField Display, Prime, Forte, IntervalVec;
	GridLayout grid1, grid2;
	Label label, For, Dis, Prim;
	
	//create overall variables
	int[] setmembers;
	int nummembers;
	int[] gintvec;
	int gintveclong;
	int[] vectortable;
	int[] zornot;
	int gzornot;
	String[] Fortetable;
	String[] Primetable;
	String gsetname;
	String gsetcontents;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, "Error setting look and feel.");
		}
		new SetHelper();
	}
	
	// start init	
	public SetHelper() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Set Helper 1.5");
        this.setSize(280, 240);
		//initialize variables
		setmembers = new int[9];
		gintvec = new int[6];
		nummembers = 0;
		vectortable = new int[209];
		Fortetable = new String[209];
		Primetable = new String[209];
		zornot = new int[209];
		
		//read in sets
		vectortable[0]=210000; Fortetable[0]="3-1"; Primetable[0]="{0,1,2}"; zornot[0] = 0;
		vectortable[1]=111000; Fortetable[1]="3-2"; Primetable[1]="{0,1,3}"; zornot[1] = 0;
		vectortable[2]=101100; Fortetable[2]="3-3"; Primetable[2]="{0,1,4}"; zornot[2] = 0;
		vectortable[3]=100110; Fortetable[3]="3-4"; Primetable[3]="{0,1,5}"; zornot[3] = 0;
		vectortable[4]=100011; Fortetable[4]="3-5"; Primetable[4]="{0,1,6}"; zornot[4] = 0;
		vectortable[5]= 20100; Fortetable[5]="3-6"; Primetable[5]="{0,2,4}"; zornot[5] = 0;
		vectortable[6]= 11010; Fortetable[6]="3-7"; Primetable[6]="{0,2,5}"; zornot[6] = 0;
		vectortable[7]= 10101; Fortetable[7]="3-8"; Primetable[7]="{0,2,6}"; zornot[7] = 0;
		vectortable[8]= 10020; Fortetable[8]="3-9"; Primetable[8]="{0,2,7}"; zornot[8] = 0;
		vectortable[9]=  2001; Fortetable[9]="3-10"; Primetable[9]="{0,3,6}"; zornot[9] = 0;
		vectortable[10]=  1110; Fortetable[10]="3-11"; Primetable[10]="{0,3,7}"; zornot[10] = 0;
		vectortable[11]=   300; Fortetable[11]="3-12"; Primetable[11]="{0,4,8}"; zornot[11] = 0;
		vectortable[12]=876663; Fortetable[12]="9-1"; Primetable[12]="{0,1,2,3,4,5,6,7,8}"; zornot[12] = 0;
		vectortable[13]=777663; Fortetable[13]="9-2"; Primetable[13]="{0,1,2,3,4,5,6,7,9}";zornot[13] = 0;
		vectortable[14]=767763; Fortetable[14]="9-3"; Primetable[14]="{0,1,2,3,4,5,6,8,9}";zornot[14] = 0;
		vectortable[15]=766773; Fortetable[15]="9-4"; Primetable[15]="{0,1,2,3,4,5,7,8,9}";zornot[15] = 0;
		vectortable[16]=766674; Fortetable[16]="9-5"; Primetable[16]="{0,1,2,3,4,6,7,8,9}";zornot[16] = 0;
		vectortable[17]=686763; Fortetable[17]="9-6"; Primetable[17]="{0,1,2,3,4,5,6,8,10}";zornot[17] = 0;
		vectortable[18]=677673; Fortetable[18]="9-7"; Primetable[18]="{0,1,2,3,4,5,7,8,10}";zornot[18] = 0;
		vectortable[19]=676764; Fortetable[19]="9-8"; Primetable[19]="{0,1,2,3,4,6,7,8,10}";zornot[19] = 0;
		vectortable[20]=676683; Fortetable[20]="9-9"; Primetable[20]="{0,1,2,3,5,6,7,8,10}";zornot[20] = 0;
		vectortable[21]=668664; Fortetable[21]="9-10"; Primetable[21]="{0,1,2,3,4,6,7,9,10}";zornot[21] = 0;
		vectortable[22]=667773; Fortetable[22]="9-11"; Primetable[22]="{0,1,2,3,5,6,7,9,10}";zornot[22] = 0;
		vectortable[23]=666963; Fortetable[23]="9-12"; Primetable[23]="{0,1,2,4,5,6,8,9,10}";zornot[23] = 0;
		vectortable[24]=321000; Fortetable[24]="4-1"; Primetable[24]="{0,1,2,3}";zornot[24] = 0;
		vectortable[25]=221100; Fortetable[25]="4-2"; Primetable[25]="{0,1,2,4}";zornot[25] = 0;
		vectortable[26]=211110; Fortetable[26]="4-4"; Primetable[26]="{0,1,2,5}";zornot[26] = 0;
		vectortable[27]=210111; Fortetable[27]="4-5"; Primetable[27]="{0,1,2,6}";zornot[27] = 0;
		vectortable[28]=210021; Fortetable[28]="4-6"; Primetable[28]="{0,1,2,7}";zornot[28] = 0;
		vectortable[29]=212100; Fortetable[29]="4-3"; Primetable[29]="{0,1,3,4}";zornot[29] = 0;
		vectortable[30]=121110; Fortetable[30]="4-11"; Primetable[30]="{0,1,3,5}";zornot[30] = 0;
		vectortable[31]=112011; Fortetable[31]="4-13"; Primetable[31]="{0,1,3,6}";zornot[31] = 0;
		vectortable[32]=111111; Fortetable[32]="4-Z29"; Primetable[32]="{0,1,3,7}"; zornot[32]=1429;
		vectortable[33]=201210; Fortetable[33]="4-7"; Primetable[33]="{0,1,4,5}"; zornot[33]=0;
		vectortable[34]=111111; Fortetable[34]="4-Z15"; Primetable[34]="{0,1,4,6}"; zornot[34]=1415;
		vectortable[35]=102111; Fortetable[35]="4-18"; Primetable[35]="{0,1,4,7}";zornot[35]=0;
		vectortable[36]=101310; Fortetable[36]="4-19"; Primetable[36]="{0,1,4,8}";zornot[36]=0;
		vectortable[37]=200121; Fortetable[37]="4-8";  Primetable[37]="{0,1,5,6}";zornot[37]=0;
		vectortable[38]=110121; Fortetable[38]="4-16"; Primetable[38]="{0,1,5,7}";zornot[38]=0;
		vectortable[39]=101220; Fortetable[39]="4-20"; Primetable[39]="{0,1,5,8}";zornot[39]=0;
		vectortable[40]=200022; Fortetable[40]="4-9";  Primetable[40]="{0,1,6,7}";zornot[40]=0;
		vectortable[41]=122010; Fortetable[41]="4-10"; Primetable[41]="{0,2,3,5}";zornot[41]=0;
		vectortable[42]=112101; Fortetable[42]="4-12"; Primetable[42]="{0,2,3,6}";zornot[42]=0;
		vectortable[43]=111120; Fortetable[43]="4-14"; Primetable[43]="{0,2,3,7}";zornot[43]=0;
		vectortable[44]= 30201; Fortetable[44]="4-21"; Primetable[44]="{0,2,4,6}";zornot[44]=0;
		vectortable[45]= 21120; Fortetable[45]="4-22"; Primetable[45]="{0,2,4,7}";zornot[45]=0;
		vectortable[46]= 20301; Fortetable[46]="4-24"; Primetable[46]="{0,2,4,8}";zornot[46]=0;
		vectortable[47]= 21030; Fortetable[47]="4-23"; Primetable[47]="{0,2,5,7}";zornot[47]=0;
		vectortable[48]= 12111; Fortetable[48]="4-27"; Primetable[48]="{0,2,5,8}";zornot[48]=0;
		vectortable[49]= 20202; Fortetable[49]="4-25"; Primetable[49]="{0,2,6,8}";zornot[49]=0;
		vectortable[50]=102210; Fortetable[50]="4-17"; Primetable[50]="{0,3,4,7}";zornot[50]=0;
		vectortable[51]= 12120; Fortetable[51]="4-26"; Primetable[51]="{0,3,5,8}";zornot[51]=0;
		vectortable[52]=  4002; Fortetable[52]="4-28"; Primetable[52]="{0,3,6,9}";zornot[52]=0;
		vectortable[53]=765442; Fortetable[53]="8-1"; Primetable[53]="{0,1,2,3,4,5,6,7}";zornot[53]=0;
		vectortable[54]=665542; Fortetable[54]="8-2"; Primetable[54]="{0,1,2,3,4,5,6,8}";zornot[54]=0;
		vectortable[55]=655552; Fortetable[55]="8-4"; Primetable[55]="{0,1,2,3,4,5,7,8}";zornot[55]=0;
		vectortable[56]=654553; Fortetable[56]="8-5"; Primetable[56]="{0,1,2,3,4,6,7,8}";zornot[56]=0;
		vectortable[57]=654463; Fortetable[57]="8-6"; Primetable[57]="{0,1,2,3,5,6,7,8}";zornot[57]=0;
		vectortable[58]=656542; Fortetable[58]="8-3"; Primetable[58]="{0,1,2,3,4,5,6,9}";zornot[58]=0;
		vectortable[59]=565552; Fortetable[59]="8-11"; Primetable[59]="{0,1,2,3,4,5,7,9}";zornot[59]=0;
		vectortable[60]=556453; Fortetable[60]="8-13"; Primetable[60]="{0,1,2,3,5,6,7,9}";zornot[60]=0;
		vectortable[63]=555553; Fortetable[63]="8-z29"; Primetable[63]="{0,1,2,3,5,6,7,9}";zornot[63]=1829;
		vectortable[62]=645652; Fortetable[62]="8-7"; Primetable[62]="{0,1,2,3,4,5,8,9}";zornot[62]=0;
		vectortable[61]=555553; Fortetable[61]="8-z15"; Primetable[61]="{0,1,2,3,4,6,8,9}";zornot[61]=1815;
		vectortable[64]=546553; Fortetable[64]="8-18"; Primetable[64]="{0,1,2,3,5,6,8,9}";zornot[64]=0;
		vectortable[65]=545752; Fortetable[65]="8-19"; Primetable[65]="{0,1,2,4,5,6,8,9}";zornot[65]=0;
		vectortable[66]=644563; Fortetable[66]="8-8"; Primetable[66]="{0,1,2,3,4,7,8,9}";zornot[66]=0;
		vectortable[67]=554563; Fortetable[67]="8-16"; Primetable[67]="{0,1,2,3,5,7,8,9}";zornot[67]=0;
		vectortable[68]=545662; Fortetable[68]="8-20"; Primetable[68]="{0,1,2,4,5,7,8,9}";zornot[68]=0;
		vectortable[69]=644464; Fortetable[69]="8-9"; Primetable[69]="{0,1,2,3,6,7,8,9}";zornot[69]=0;
		vectortable[70]=566452; Fortetable[70]="8-10"; Primetable[70]="{0,2,3,4,5,6,7,9}";zornot[70]=0;
		vectortable[71]=556543; Fortetable[71]="8-12"; Primetable[71]="{0,1,3,4,5,6,7,9}";zornot[71]=0;
		vectortable[72]=555562; Fortetable[72]="8-14"; Primetable[72]="{0,1,2,4,5,6,7,9}";zornot[72]=0;
		vectortable[73]=474643; Fortetable[73]="8-21"; Primetable[73]="{0,1,2,3,4,6,8,10}";zornot[73]=0;
		vectortable[74]=465562; Fortetable[74]="8-22"; Primetable[74]="{0,1,2,3,5,6,8,10}";zornot[74]=0;
		vectortable[75]=464743; Fortetable[75]="8-24"; Primetable[75]="{0,1,2,4,5,6,8,10}";zornot[75]=0;
		vectortable[76]=465472; Fortetable[76]="8-23"; Primetable[76]="{0,1,2,3,5,7,8,10}";zornot[76]=0;
		vectortable[77]=456553; Fortetable[77]="8-27"; Primetable[77]="{0,1,2,4,5,7,8,10}";zornot[77]=0;
		vectortable[78]=464644; Fortetable[78]="8-25"; Primetable[78]="{0,1,2,4,6,7,8,10}";zornot[78]=0;
		vectortable[79]=546652; Fortetable[79]="8-17"; Primetable[79]="{0,1,3,4,5,6,8,9}";zornot[79]=0;
		vectortable[80]=456562; Fortetable[80]="8-26"; Primetable[80]="{0,1,3,4,5,7,8,10}";zornot[80]=0;
		vectortable[81]=448444; Fortetable[81]="8-28"; Primetable[81]="{0,1,3,4,6,7,9,10}";zornot[81]=0;
		vectortable[82]=654321; Fortetable[82]="7-1"; Primetable[82]="{0,1,2,3,4,5,6}";zornot[82]=0;
		vectortable[83]=554331; Fortetable[83]="7-2"; Primetable[83]="{0,1,2,3,4,5,7}";zornot[83]=0;
		vectortable[84]=544332; Fortetable[84]="7-4"; Primetable[84]="{0,1,2,3,4,6,7}";zornot[84]=0;
		vectortable[85]=543342; Fortetable[85]="7-5"; Primetable[85]="{0,1,2,3,5,6,7}";zornot[85]=0;
		vectortable[86]=544431; Fortetable[86]="7-3"; Primetable[86]="{0,1,2,3,4,5,8}";zornot[86]=0;
		vectortable[87]=453432; Fortetable[87]="7-9"; Primetable[87]="{0,1,2,3,4,6,8}";zornot[87]=0;
		vectortable[88]=444342; Fortetable[88]="7-z36"; Primetable[88]="{0,1,2,3,5,6,8}";zornot[88]=1736;
		vectortable[89]=443532; Fortetable[89]="7-13"; Primetable[89]="{0,1,2,4,5,6,8}";zornot[89]=0;
		vectortable[90]=533442; Fortetable[90]="7-6"; Primetable[90]="{0,1,2,3,4,7,8}";zornot[90]=0;
		vectortable[91]=443352; Fortetable[91]="7-14"; Primetable[91]="{0,1,2,3,5,7,8}";zornot[91]=0;
		vectortable[92]=434442; Fortetable[92]="7-z38"; Primetable[92]="{0,1,2,4,5,7,8}";zornot[92]=1738;
		vectortable[93]=532353; Fortetable[93]="7-7"; Primetable[93]="{0,1,2,3,6,7,8}";zornot[93]=0;
		vectortable[94]=442443; Fortetable[94]="7-15"; Primetable[94]="{0,1,2,4,6,7,8}";zornot[94]=0;
		vectortable[95]=445332; Fortetable[95]="7-10"; Primetable[95]="{0,1,2,3,4,6,9}";zornot[95]=0;
		vectortable[96]=435432; Fortetable[96]="7-16"; Primetable[96]="{0,1,2,3,5,6,9}";zornot[96]=0;
		vectortable[119]=434541; Fortetable[119]="7-z17"; Primetable[119]="{0,1,2,4,5,6,9}";zornot[119]=1717;
		vectortable[98]=444342; Fortetable[98]="7-z12"; Primetable[98]="{0,1,2,3,4,7,9}";zornot[98]=1712;
		vectortable[99]=353442; Fortetable[99]="7-24"; Primetable[99]="{0,1,2,3,5,7,9}";zornot[99]=0;
		vectortable[100]=344451; Fortetable[100]="7-27"; Primetable[100]="{0,1,2,4,5,7,9}";zornot[100]=0;
		vectortable[101]=434343; Fortetable[101]="7-19"; Primetable[101]="{0,1,2,3,6,7,9}";zornot[101]=0;
		vectortable[102]=344352; Fortetable[102]="7-29"; Primetable[102]="{0,1,2,4,6,7,9}";zornot[102]=0;
		vectortable[103]=336333; Fortetable[103]="7-31"; Primetable[103]="{0,1,3,4,6,7,9}";zornot[103]=0;
		vectortable[104]=434442; Fortetable[104]="7-z18"; Primetable[104]="{0,1,4,5,6,7,9}";zornot[104]=1718;
		vectortable[105]=424641; Fortetable[105]="7-21"; Primetable[105]="{0,1,2,4,5,8,9}";zornot[105]=0;
		vectortable[106]=343542; Fortetable[106]="7-30"; Primetable[106]="{0,1,2,4,6,8,9}";zornot[106]=0;
		vectortable[107]=335442; Fortetable[107]="7-32"; Primetable[107]="{0,1,3,4,6,8,9}";zornot[107]=0;
		vectortable[108]=424542; Fortetable[108]="7-22"; Primetable[108]="{0,1,2,5,6,8,9}";zornot[108]=0;
		vectortable[109]=433452; Fortetable[109]="7-20"; Primetable[109]="{0,1,3,5,6,7,9}";zornot[109]=0;
		vectortable[110]=454422; Fortetable[110]="7-8"; Primetable[110]="{0,2,3,4,5,6,8}";zornot[110]=0;
		vectortable[111]=444441; Fortetable[111]="7-11"; Primetable[111]="{0,1,3,4,5,6,8}";zornot[111]=0;
		vectortable[112]=354351; Fortetable[112]="7-23"; Primetable[112]="{0,2,3,4,5,7,9}";zornot[112]=0;
		vectortable[113]=345342; Fortetable[113]="7-25"; Primetable[113]="{0,2,3,4,6,7,9}";zornot[113]=0;
		vectortable[114]=344433; Fortetable[114]="7-28"; Primetable[114]="{0,1,3,5,6,7,9}";zornot[114]=0;
		vectortable[115]=344532; Fortetable[115]="7-26"; Primetable[115]="{0,1,3,4,5,7,9}";zornot[115]=0;
		vectortable[116]=262623; Fortetable[116]="7-33"; Primetable[116]="{0,1,2,4,6,8,10}";zornot[116]=0;
		vectortable[117]=254442; Fortetable[117]="7-34"; Primetable[117]="{0,1,3,4,6,8,10}";zornot[117]=0;
		vectortable[118]=254361; Fortetable[118]="7-35"; Primetable[118]="{0,1,3,5,6,8,10}";zornot[118]=0;
		vectortable[97]=434541; Fortetable[97]="7-z37"; Primetable[97]="{0,1,3,4,5,7,8}";zornot[97]=1737;
		
		vectortable[120]=432100; Fortetable[120]="5-1"; Primetable[120]="{0,1,2,3,4}";zornot[120]=0;
		vectortable[121]=332110; Fortetable[121]="5-2"; Primetable[121]="{0,1,2,3,5}";zornot[121]=0;
		vectortable[122]=322111; Fortetable[122]="5-4"; Primetable[122]="{0,1,2,3,6}";zornot[122]=0;
		vectortable[123]=321121; Fortetable[123]="5-5"; Primetable[123]="{0,1,2,3,7}";zornot[123]=0;
		vectortable[124]=322210; Fortetable[124]="5-3"; Primetable[124]="{0,1,2,4,5}";zornot[124]=0;
		vectortable[125]=231211; Fortetable[125]="5-9"; Primetable[125]="{0,1,2,4,6}";zornot[125]=0;
		vectortable[126]=222121; Fortetable[126]="5-z36"; Primetable[126]="{0,1,2,4,7}";zornot[126]=1536;
		vectortable[127]=221311; Fortetable[127]="5-13"; Primetable[127]="{0,1,2,4,8}";zornot[127]=0;
		vectortable[128]=311221; Fortetable[128]="5-6"; Primetable[128]="{0,1,2,5,6}";zornot[128]=0;
		vectortable[129]=221131; Fortetable[129]="5-14"; Primetable[129]="{0,1,2,5,7}";zornot[129]=0;
		vectortable[130]=212221; Fortetable[130]="5-z38"; Primetable[130]="{0,1,2,5,8}";zornot[130]=1538;
		vectortable[131]=310132; Fortetable[131]="5-7"; Primetable[131]="{0,1,2,6,7}";zornot[131]=0;
		vectortable[132]=220222; Fortetable[132]="5-15"; Primetable[132]="{0,1,2,6,8}";zornot[132]=0;
		vectortable[133]=223111; Fortetable[133]="5-10"; Primetable[133]="{0,1,3,4,6}";zornot[133]=0;
		vectortable[134]=213211; Fortetable[134]="5-16"; Primetable[134]="{0,1,3,4,7}";zornot[134]=0;
		vectortable[157]=212320; Fortetable[157]="5-z17"; Primetable[157]="{0,1,3,4,8}";zornot[157]=1517;
		vectortable[136]=222121; Fortetable[136]="5-z12"; Primetable[136]="{0,1,3,5,6}";zornot[136]=1512;
		vectortable[137]=131221; Fortetable[137]="5-24"; Primetable[137]="{0,1,3,5,7}";zornot[137]=0;
		vectortable[138]=122230; Fortetable[138]="5-27"; Primetable[138]="{0,1,3,5,8}";zornot[138]=0;
		vectortable[139]=212122; Fortetable[139]="5-19"; Primetable[139]="{0,1,3,6,7}";zornot[139]=0;
		vectortable[140]=122131; Fortetable[140]="5-29"; Primetable[140]="{0,1,3,6,8}";zornot[140]=0;
		vectortable[141]=114112; Fortetable[141]="5-31"; Primetable[141]="{0,1,3,6,9}";zornot[141]=0;
		vectortable[142]=212221; Fortetable[142]="5-z18"; Primetable[142]="{0,1,4,5,7}";zornot[142]=1518;
		vectortable[143]=202420; Fortetable[143]="5-21"; Primetable[143]="{0,1,4,5,8}";zornot[143]=0;
		vectortable[144]=121321; Fortetable[144]="5-30"; Primetable[144]="{0,1,4,6,8}";zornot[144]=0;
		vectortable[145]=113221; Fortetable[145]="5-32"; Primetable[145]="{0,1,4,6,9}";zornot[145]=0;
		vectortable[146]=202321; Fortetable[146]="5-22"; Primetable[146]="{0,1,4,7,8}";zornot[146]=0;
		vectortable[147]=211231; Fortetable[147]="5-20"; Primetable[147]="{0,1,5,6,8}";zornot[147]=0;
		vectortable[148]=232201; Fortetable[148]="5-8"; Primetable[148]="{0,2,3,4,6}";zornot[148]=0;
		vectortable[149]=222220; Fortetable[149]="5-11"; Primetable[149]="{0,2,3,4,7}";zornot[149]=0;
		vectortable[150]=132130; Fortetable[150]="5-23"; Primetable[150]="{0,2,3,5,7}";zornot[150]=0;
		vectortable[151]=123121; Fortetable[151]="5-25"; Primetable[151]="{0,2,3,5,8}";zornot[151]=0;
		vectortable[152]=122212; Fortetable[152]="5-28"; Primetable[152]="{0,2,3,6,8}";zornot[152]=0;
		vectortable[153]=122311; Fortetable[153]="5-26"; Primetable[153]="{0,2,4,5,8}";zornot[153]=0;
		vectortable[154]= 40402; Fortetable[154]="5-33"; Primetable[154]="{0,2,4,6,8}";zornot[154]=0;
		vectortable[155]= 32221; Fortetable[155]="5-34"; Primetable[155]="{0,2,4,6,9}";zornot[155]=0;
		vectortable[156]= 32140; Fortetable[156]="5-35"; Primetable[156]="{0,2,4,7,9}";zornot[156]=0;
		vectortable[207]=212320; Fortetable[207]="5-z37"; Primetable[207]="{0,3,4,5,8}";zornot[207]=1537;
		
		vectortable[208]=543210; Fortetable[208]="6-1"; Primetable[208]="{0,1,2,3,4,5}";zornot[208]=0;
		vectortable[158]=443211; Fortetable[158]="6-2"; Primetable[158]="{0,1,2,3,4,6}";zornot[158]=0;
		vectortable[159]=433221; Fortetable[159]="6-z36"; Primetable[159]="{0,1,2,3,4,7}";zornot[159]=1636;
		vectortable[160]=433221; Fortetable[160]="6-z3"; Primetable[160]="{0,1,2,3,5,6}";zornot[160]=1603;
		vectortable[161]=432321; Fortetable[161]="6-z37"; Primetable[161]="{0,1,2,3,4,8}";zornot[16]=1637;
		vectortable[162]=432321; Fortetable[162]="6-z4"; Primetable[162]="{0,1,2,4,5,6}";zornot[162]=1604;
		vectortable[163]=342231; Fortetable[163]="6-9"; Primetable[163]="{0,1,2,3,5,7}";zornot[163]=0;
		vectortable[164]=333231; Fortetable[164]="6-z40"; Primetable[164]="{0,1,2,3,5,8}";zornot[164]=1640;
		vectortable[165]=333231; Fortetable[165]="6-z11"; Primetable[165]="{0,1,2,4,5,7}";zornot[165]=1611;
		vectortable[166]=422232; Fortetable[166]="6-5"; Primetable[166]="{0,1,2,3,6,7}";zornot[166]=0;
		vectortable[167]=332232; Fortetable[167]="6-z41"; Primetable[167]="{0,1,2,3,6,8}";zornot[167]=1641;
		vectortable[168]=332232; Fortetable[168]="6-z12"; Primetable[168]="{0,1,2,4,6,7}";zornot[168]=1612;
		vectortable[169]=324222; Fortetable[169]="6-z42"; Primetable[169]="{0,1,2,3,6,9}";zornot[169]=1642;
		vectortable[170]=324222; Fortetable[170]="6-z13"; Primetable[170]="{0,1,3,4,6,7}";zornot[170]=1613;
		vectortable[171]=421242; Fortetable[171]="6-z38"; Primetable[171]="{0,1,2,3,7,8}";zornot[171]=1638;
		vectortable[172]=421242; Fortetable[172]="6-z6"; Primetable[172]="{0,1,2,5,6,7}";zornot[172]=1606;
		vectortable[173]=323421; Fortetable[173]="6-15"; Primetable[173]="{0,1,2,4,5,8}";zornot[173]=0;
		vectortable[174]=241422; Fortetable[174]="6-22"; Primetable[174]="{0,1,2,4,6,8}";zornot[174]=0;
		vectortable[175]=233331; Fortetable[175]="6-z46"; Primetable[175]="{0,1,2,4,6,9}";zornot[175]=1646;
		vectortable[176]=233331; Fortetable[176]="6-z24"; Primetable[176]="{0,1,3,4,6,8}";zornot[176]=1624;
		vectortable[177]=322332; Fortetable[177]="6-z17"; Primetable[177]="{0,1,2,4,7,8}";zornot[177]=1617;
		vectortable[178]=322332; Fortetable[178]="6-z43"; Primetable[178]="{0,1,2,5,6,8}";zornot[178]=1643;
		vectortable[179]=233241; Fortetable[179]="6-z47"; Primetable[179]="{0,1,2,4,7,9}";zornot[179]=1647;
		vectortable[180]=233241; Fortetable[180]="6-z25"; Primetable[180]="{0,1,3,5,6,8}";zornot[180]=1625;
		vectortable[182]=313431; Fortetable[182]="6-z44"; Primetable[182]="{0,1,2,5,6,9}";zornot[182]=1644;
		vectortable[181]=313431; Fortetable[181]="6-z19"; Primetable[181]="{0,1,3,4,7,8}";zornot[181]=1619;
		vectortable[183]=322242; Fortetable[183]="6-18"; Primetable[183]="{0,1,2,5,7,8}";zornot[183]=0;
		vectortable[184]=232341; Fortetable[184]="6-z48"; Primetable[184]="{0,1,2,5,7,9}";zornot[184]=1648;
		vectortable[185]=232341; Fortetable[185]="6-z26"; Primetable[185]="{0,1,3,5,7,8}";zornot[185]=1626;
		vectortable[186]=420243; Fortetable[186]="6-7"; Primetable[186]="{0,1,2,6,7,8}";zornot[186]=0;
		vectortable[187]=333321; Fortetable[187]="6-z10"; Primetable[187]="{0,1,3,4,5,7}";zornot[187]=1610;
		vectortable[188]=333321; Fortetable[188]="6-z39"; Primetable[188]="{0,2,3,4,5,8}";zornot[188]=1639;
		vectortable[189]=323430; Fortetable[189]="6-14"; Primetable[189]="{0,1,3,4,5,8}";zornot[189]=0;
		vectortable[190]=225222; Fortetable[190]="6-27"; Primetable[190]="{0,1,3,4,6,9}";zornot[190]=0;
		vectortable[192]=224322; Fortetable[192]="6-z49"; Primetable[192]="{0,1,3,4,7,9}";zornot[192]=1649;
		vectortable[191]=224322; Fortetable[191]="6-z28"; Primetable[191]="{0,1,3,5,6,9}";zornot[191]=1628;
		vectortable[193]=142422; Fortetable[193]="6-34"; Primetable[193]="{0,1,3,5,7,9}";zornot[193]=0;
		vectortable[194]=223431; Fortetable[194]="6-31"; Primetable[194]="{0,1,4,5,7,9}";zornot[194]=0;
		vectortable[195]=224223; Fortetable[195]="6-30"; Primetable[195]="{0,1,3,6,7,9}";zornot[195]=0;
		vectortable[196]=224232; Fortetable[196]="6-z29"; Primetable[196]="{0,2,3,6,7,9}";zornot[196]=1629;
		vectortable[197]=224232; Fortetable[197]="6-z50"; Primetable[197]="{0,1,4,6,7,9}";zornot[197]=1650;
		vectortable[198]=322431; Fortetable[198]="6-16"; Primetable[198]="{0,1,4,5,6,8}";zornot[198]=0;
		vectortable[199]=303630; Fortetable[199]="6-20"; Primetable[199]="{0,1,4,5,8,9}";zornot[199]=0;
		vectortable[200]=343230; Fortetable[200]="6-8"; Primetable[200]="{0,2,3,4,5,7}";zornot[200]=0;
		vectortable[201]=242412; Fortetable[201]="6-21"; Primetable[201]="{0,2,3,4,6,8}";zornot[201]=0;
		vectortable[202]=234222; Fortetable[202]="6-z45"; Primetable[202]="{0,2,3,4,6,9}";zornot[202]=1645;
		vectortable[203]=234222; Fortetable[203]="6-z23"; Primetable[203]="{0,2,3,5,6,8}";zornot[203]=1623;
		vectortable[204]=143241; Fortetable[204]="6-33"; Primetable[204]="{0,2,3,5,7,9}";zornot[204]=0;
		vectortable[205]=143250; Fortetable[205]="6-32"; Primetable[205]="{0,2,4,5,7,9}";zornot[205]=0;
		vectortable[206]= 60603; Fortetable[206]="6-35"; Primetable[206]="{0,2,4,6,8,10}";zornot[206]=0;

		//create buttons, fields, etc.
		label = new Label("Interval Vector:");
		For = new Label("Forte Number:");
		Dis = new Label("Set:");
		Prim = new Label("Prime Form:");
			
		Display = new TextField(17);
		Display.setEditable(false);
		
		Prime = new TextField(17);
		Prime.setEditable(false);
		
		Forte = new TextField(5);
		Forte.setEditable(false);
		
		IntervalVec = new TextField(8);
		IntervalVec.setEditable(false);
		
		Zero = new Button("0");
		One = new Button("1");
		Two = new Button("2");
		Three = new Button("3");
		Four = new Button("4");
		Five = new Button("5");
		Six = new Button("6");
		Seven = new Button("7");
		Eight = new Button("8");
		Nine = new Button("9");
		Ten = new Button("10");
		Eleven = new Button("11");
		Reset = new Button("Reset");
		
		ButtonPanel = new Panel();
		
		grid1 = new GridLayout(4,3);
		ButtonPanel.setLayout(grid1);
		
		ButtonPanel.add(Zero);
		ButtonPanel.add(One);
		ButtonPanel.add(Two);
		ButtonPanel.add(Three);
		ButtonPanel.add(Four);
		ButtonPanel.add(Five);
		ButtonPanel.add(Six);
		ButtonPanel.add(Seven);
		ButtonPanel.add(Eight);
		ButtonPanel.add(Nine);
		ButtonPanel.add(Ten);
		ButtonPanel.add(Eleven);
		
		grid2 = new GridLayout(4,2);
		GridPanel = new Panel();
		GridPanel.setLayout(grid2);
		
		GridPanel.add(For);
		GridPanel.add(Forte);
		GridPanel.add(label);
		GridPanel.add(IntervalVec);
		GridPanel.add(Prim);
		GridPanel.add(Prime);
		GridPanel.add(Dis);
		GridPanel.add(Display);
		//display buttons, fields, etc.
		JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);
        contentPane.add(GridPanel);
        contentPane.add(Reset);
        contentPane.add(ButtonPanel);
        this.setVisible(true);
	}
		
//event loop
public boolean handleEvent(Event e)		
	{
	switch(e.id)
		{
		case Event.ACTION_EVENT:
			{
			//Number() if any number clicked StartOver() if reset clicked
			if (e.target == Reset) StartOver();
			if (e.target == Zero) Number(0);
			if (e.target == One) Number(1);
			if (e.target == Two) Number(2);
			if (e.target == Three) Number(3);
			if (e.target == Four) Number(4);
			if (e.target == Five) Number(5);
			if (e.target == Six) Number(6);
			if (e.target == Seven) Number(7);
			if (e.target == Eight) Number(8);
			if (e.target == Nine) Number(9);
			if (e.target == Ten) Number(10);
			if (e.target == Eleven) Number(11);
			}
		default:
		}
	return true;
	}


//StartOver() resets all fields and variables
public void StartOver()
	{
	//local variable
	int loop = 0;
	//clear out fields
	Display.setText("");
	Prime.setText("");
	Forte.setText("");
	IntervalVec.setText("");
	//clear out variables
	nummembers = 0;
	while (loop < 9)
		{
		setmembers[loop] = 0;
		loop = loop +1;
		}
	}

//Number() takes the number clicked and adds it to the array, if appropriate
public void Number(int input)
	{
	//local vars
	boolean keepgoing = true, looping = true;
	int counter = 0;
	String setdisplay;
	
	//if already 9 members in array do nothing
	if (nummembers == 9)
		keepgoing = false;
	
	//if number already in array do nothing
	if (nummembers > 0)
		{
		while (looping == true)
			{
			if (setmembers[counter] == input)
				keepgoing = false;
			counter = counter + 1;
			if (counter == nummembers)
				looping = false;
			}
		}
	
	//add number to array
	if (keepgoing)
		{	
		nummembers = nummembers + 1;
		setmembers[nummembers-1]  = input;

		//Calculate set info
		setinfo();
		
		//Display set in its window
		Display.setText("{");
		counter = 0;
		while (counter < nummembers)
			{
			//add number to string
			setdisplay = Display.getText();
			Display.setText(setdisplay + setmembers[counter]);
			//add comma if not last number
			if (counter < (nummembers-1))
				{
				setdisplay = Display.getText();
				Display.setText(setdisplay + ",");
				}
			counter = counter +1;
			}
		//complete set display
		setdisplay = Display.getText();
		Display.setText(setdisplay + "}");
		//Display interval vector in its window
		IntervalVec.setText("[" + gintvec[0] + gintvec[1] + gintvec[2] + gintvec[3] + 
			gintvec[4] + gintvec[5] + "]");
		
		//Display prime form in its window
		if (nummembers>2)
			{
			Prime.setText(gsetcontents);
			Forte.setText(gsetname);
			}
		
		}
	}

//set info calculator
public void setinfo()
	{
	//local vars
	boolean whoops = true;
	int loop=0, great=-1, highnum=0, tempset[], bigloop=0;
	tempset = new int[12];
	//line setmembers up in ascending order
	while (bigloop<nummembers)
		{
		while (loop < (nummembers))
			{
			if (setmembers[loop]>great)
				{
				great = setmembers[loop];
				highnum=loop;
				}

			loop = loop +1;
			}
			
		tempset[bigloop] = great;
		setmembers[highnum] = -1;
		great = -1;
		loop=0;
		bigloop = bigloop +1;
		}
	
	//Read tempset back into gset
	loop = 0;
	while (loop<nummembers)				
		{
		setmembers[nummembers-(loop+1)] = tempset[loop];
		loop = loop + 1;
		}

	//setmembers[] has now been sorted in descending order!
	//time to do the interval vector!!!!
	//Initialize gintvec
	loop = 0;							
	while (loop < 6)
		{
		gintvec[loop] = 0;
		loop = loop + 1;
		}
	bigloop = 0;
	loop = 1;
	highnum = 0;
	
	while (bigloop < (nummembers-1) )
		{
		loop = bigloop+1;
		while (loop < nummembers )
			{
			//this
			highnum = setmembers[loop] - setmembers[bigloop];	
			if (highnum>6)
				{
				highnum = 12-highnum;
				}
			gintvec[highnum-1] = (gintvec[highnum-1]) + 1;
			loop = loop + 1;
			} 
		bigloop = bigloop + 1;
		}

	//create gintveclong									
	gintveclong = gintvec[5] + (10*gintvec[4]) + (100*gintvec[3]) + 
	(1000*gintvec[2]) + (10000*(gintvec[1])) + (100000*(gintvec[0]));
	
	
	//Find the set from the library that matches setmembers[]
	if (nummembers > 2)
		{
		loop=0;
		while (loop<209)
			{
			if (gintveclong==vectortable[loop])
				{
				gsetname=Fortetable[loop];
				gsetcontents=Primetable[loop];
				gzornot=zornot[loop];
				whoops=false;
				}
			loop = loop + 1;
			}
		if (whoops == true)
			{
			gsetname="Error!";
			gsetcontents="Error!";
			}
		if (gzornot>999)
			{
			zset();
			}
		}
	}

//zset determiner
public void zset()
	{
	//local vars
	int loop = 0, lope = 0, lop = 0, lo = 0, numb1 = 0, numb2 = 0, numb3 = 0;
	switch (gzornot)
		{
		case 1415:
			{
			while (loop < 4)
				{
				if (loop==0)
					lope = 3;
				else
					lope = loop - 1;
				numb1 = (setmembers[loop] - setmembers[lope]);
				if (numb1<0)
					numb1 = numb1 + 12;
				if (numb1==4)
					{
					gsetname="4-Z29"; gsetcontents="{0,1,3,7}";
					}
				loop = loop + 1;
				}
			break;
			}

		case 1829:
			{
			while (loop < 8)
				{
				if (loop==0)
					lope = 7;
				else
					lope = loop - 1;
			
				if (lope==0)
					lop = 7;
				else
					lop = lope - 1;
			
				numb1 = (setmembers[loop] - setmembers[lope]);
				if (numb1<0)
					numb1 = numb1 + 12;
				numb2 = (setmembers[lope] - setmembers[lop]);
				if (numb2<0)
					numb2 = numb2 + 12;
				
				if ((numb1==2)&&(numb2==2))
					{
					gsetname="8z-15"; gsetcontents="{0,1,2,3,4,6,8,9}";
					}
				loop = loop + 1;
				}
			break;
			}

		
		
		
case 1517:
{	while (loop < 5)
		{
		if (loop==0)
			lope = 4;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==3)
			{
			gsetname="5-z17"; gsetcontents="{0,3,4,5,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1518:
{	while (loop < 5)
		{
		if (loop==0)
			lope = 4;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==4)
			{
			gsetname="5-z38"; gsetcontents="{0,1,2,5,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1512:
{	while (loop < 5)
		{
		if (loop==0)
			lope = 4;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==5)
			{
			gsetname="5-z36"; gsetcontents="{0,1,2,4,7}";
			}
		loop = loop + 1;
		}
break;
}

case 1717:
{	while (loop < 7)
		{
		if (loop==0)
			lope = 6;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==4)
			{
			gsetname="7-z37"; gsetcontents="{0,1,3,4,5,7,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1718:
{	while (loop < 7)
		{
		if (loop==0)
			lope = 6;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==4)
			{
			gsetname="7-z38"; gsetcontents="{0,1,2,4,5,7,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1712:
{	while (loop < 7)
		{
		if (loop==0)
			lope = 6;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==4)
			{
			gsetname="7-z36"; gsetcontents="{0,1,2,3,5,6,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1603:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==5)
			{
			gsetname="6-z36"; gsetcontents="{0,1,2,3,4,7}";
			}
		loop = loop + 1;
		}
break;
}

case 1604:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==4)
			{
			gsetname="6-z37"; gsetcontents="{0,1,2,3,4,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1611:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==4)
			{
			gsetname="6-z40"; gsetcontents="{0,1,2,3,5,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1612:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==4)
			{
			gsetname="6-z41"; gsetcontents="{0,1,2,3,6,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1613:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==3)
			{
			gsetname="6-z42"; gsetcontents="{0,1,2,3,6,9}";
			}
		loop = loop + 1;
		}
break;
}

case 1606:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==4)
			{
			gsetname="6-z38"; gsetcontents="{0,1,2,3,7,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1624:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==3)
			{
			gsetname="6-z46"; gsetcontents="{0,1,2,4,6,9}";
			}
		loop = loop + 1;
		}
break;
}
case 1643:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
			
		if (lope==0)
			lop = 5;
		else
			lop = lope - 1;
			
		numb1 = (setmembers[loop] - setmembers[lope]);
		if (numb1<0)
			numb1 = numb1 + 12;
		numb2 = (setmembers[lope] - setmembers[lop]);
		if (numb2<0)
			numb2 = numb2 + 12;
				
		if ((numb1==2)&&(numb2==3))
			{
			gsetname="6-z17"; gsetcontents="{0,1,2,4,7,8}";
			}
		
			if ((numb1==3)&&(numb2==2))
			{
			gsetname="6-z17"; gsetcontents="{0,1,2,4,7,8}";
			}
		loop = loop + 1;
		}
break;
}
case 1625:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==3)
			{
			gsetname="6-z47"; gsetcontents="{0,1,2,4,7,9}";
			}
		loop = loop + 1;
		}
break;
}


case 1644:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==4)
			{
			gsetname="6-z19"; gsetcontents="{0,1,3,4,7,8}";
			}
		loop = loop + 1;
		}
break;
}

case 1626:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==3)
			{
			gsetname="6-z48"; gsetcontents="{0,1,2,5,7,9}";
			}
		loop = loop + 1;
		}
break;
}

case 1639:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==5)
			{
			gsetname="6-z10"; gsetcontents="{0,1,3,4,5,7}";
			}
		loop = loop + 1;
		}
break;
}

case 1649:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
			
		if (lope==0)
			lop = 5;
		else
			lop = lope - 1;
			
		numb1 = (setmembers[loop] - setmembers[lope]);
		if (numb1<0)
			numb1 = numb1 + 12;
		numb2 = (setmembers[lope] - setmembers[lop]);
		if (numb2<0)
			numb2 = numb2 + 12;
				
		if ((numb1==3)&&(numb2==3))
			{
			gsetname="6-z28"; gsetcontents="{0,1,3,5,6,9}";
			}
		loop = loop + 1;
		}
break;
}

case 1650:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
			
		if (lope==0)
			lop = 5;
		else
			lop = lope - 1;
			
		if (lop==0)
			lo = 5;
		else
			lo = lop - 1;

		numb1 = (setmembers[loop] - setmembers[lope]);
		if (numb1<0)
			numb1 = numb1 + 12;
		numb2 = (setmembers[lope] - setmembers[lop]);
		if (numb2<0)
			numb2 = numb2 + 12;
		numb3 = (setmembers[lop] - setmembers[lo]);
		if (numb3<0)
			numb3 = numb3 + 12;
				
		if ((numb1==2)&&(numb2==1)&&(numb3==3))
			{
			gsetname="6-z29"; gsetcontents="{0,2,3,6,7,9}";
			}
		if ((numb1==3)&&(numb2==1)&&(numb3==2))
			{
			gsetname="6-z29"; gsetcontents="{0,2,3,6,7,9}";
			}
		loop = loop + 1;
		}
break;
}

case 1623:
{	while (loop < 6)
		{
		if (loop==0)
			lope = 5;
		else
			lope = loop - 1;
		
		numb1 = (setmembers[loop] - setmembers[lope]);
		
		if (numb1<0)
			numb1 = numb1 + 12;
		if (numb1==3)
			{
			gsetname="6-z45"; gsetcontents="{0,2,3,4,6,9}";
			}
		loop = loop + 1;
		}
break;
}
				
		
		}
	}
}
