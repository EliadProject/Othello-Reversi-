package game;

public class Board {
	private Disk[][] disks;
	private int numDisks;

	public Board() {
		super();
		//
		this.disks = new Disk[9][9];
		this.disks[4][4] = new Disk(Color.WHITE);
		this.disks[4][5] = new Disk(Color.BLACK);
		this.disks[5][4] = new Disk(Color.BLACK);
		this.disks[5][5] = new Disk(Color.WHITE);
		this.numDisks = 0;

	}

	public boolean isGame(Color color) {
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (this.disks[i][j] != null) {
					if (this.disks[i][j].getColor() == color) {
						if(checkPossible(i,j,color))
							return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkPossible(int i, int j, Color color) {
		Color opponentColor;
		if (color == Color.WHITE) {
			opponentColor = Color.BLACK;
		} else
			opponentColor = Color.WHITE;

		// Check East
		int checkI = i;
		int checkJ = j + 1;

		if (isInBorderY(checkJ)) {
			while (this.disks[checkI][checkJ] != null && (this.disks[checkI][checkJ].getColor() == opponentColor)) {

				checkJ +=  1;
				if (!isInBorderY(checkJ)) {
					break;
				}
				if (this.disks[checkI][checkJ] == null) {
					return true;
				}
			}
		}

		// Check South East
		checkI = i + 1;
		checkJ = j + 1;

		if (isInBorderX(checkI) && isInBorderY(checkJ)) {
			while (this.disks[checkI][checkJ] != null && (this.disks[checkI][checkJ].getColor() == opponentColor)) {

				checkI += 1;
				checkJ += 1;
				if (!isInBorderX(checkI) || !isInBorderY(checkJ)) {
					break;
				}
				if (this.disks[checkI][checkJ] == null) {
					return true;
				}
			}
		}

		// Check South
		checkI = i + 1;
		checkJ = j;

		if (isInBorderX(checkI)) {
			while (this.disks[checkI][checkJ] != null && (this.disks[checkI][checkJ].getColor() == opponentColor)) {

				checkI += 1;
				if (!isInBorderX(checkI)) {
					break;
				}
				if (this.disks[checkI][checkJ] == null) {
					return true;
				}
			}
		}

		// Check South West
		checkI = i + 1;
		checkJ = j - 1;

		if (isInBorderX(checkI) && isInBorderY(checkJ)) {
			while (this.disks[checkI][checkJ] != null && (this.disks[checkI][checkJ].getColor() == opponentColor)) {

				checkI += 1;
				checkJ -= 1;
				if (!isInBorderX(checkI) || !isInBorderY(checkJ)) {
					break;
				}
				if (this.disks[checkI][checkJ] == null) {
					return true;
				}
			}
		}
		return false;

	}
	
	public PlayStatus play(int iTo, int jTo,Color color)
	{
		PlayStatus status = PlayStatus.NotPossibole;
		if(this.disks[iTo][jTo] != null)
		{
			//Not possibole to play with already taken disk
			return status;
		}
		Color opponentColor;
		if (color == Color.WHITE) {
			opponentColor = Color.BLACK;
		} else
			opponentColor = Color.WHITE;
		
		int checkI,checkJ;
		
		//North Path
		checkI= iTo -1;
		checkJ = jTo;
		
		if(isInBorderX(checkI))
		{
			while(this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == opponentColor )
			{
				checkI= checkI -1;
				if (!isInBorderX(checkI) ) {
					break;
				}
				if (this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == color) {
					{
						status = PlayStatus.OK;
						//Go back and flip color
						checkI= checkI +1;
						while(checkI!=iTo)
						{
							this.disks[checkI][checkJ].setColor(color);
							checkI= checkI +1;
							
						}
						
					}
				}
			}
		}
		
		//North East Path
				checkI= iTo -1;
				checkJ = jTo +1;
				
				if(isInBorderX(checkI) && isInBorderY(checkJ))
				{
					while(this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == opponentColor )
					{
						checkI-= 1;
						checkJ += 1;
						if (!(isInBorderX(checkI) && isInBorderY(checkJ))) {
							break;
						}
						if (this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == color) {
							{
								status = PlayStatus.OK;
								//Go back and flip color
								checkI= checkI +1;
								checkJ = checkJ -1;
								while(checkI!=iTo)
								{
									this.disks[checkI][checkJ].setColor(color);
									checkI= checkI +1;
									checkJ = checkJ -1;
									
								}
								
							}
						}
					}
				}
				
				//East Path
				checkI= iTo ;
				checkJ = jTo +1;
				
				if(isInBorderY(checkJ))
				{
					while(this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == opponentColor )
					{
						checkJ += 1;
						if (!(isInBorderY(checkJ))) {
							break;
						}
						if (this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == color) {
							{
								status = PlayStatus.OK;
								//Go back and flip color
								checkJ = checkJ -1;
								while(checkJ!=jTo)
								{
									
									this.disks[checkI][checkJ].setColor(color);
									checkJ = checkJ -1;
								}
								
							}
						}
					}
				}
				
				
				//South East Path
				checkI= iTo +1 ;
				checkJ = jTo +1;
				
				if(isInBorderX(checkI) && isInBorderY(checkJ) )
				{
					while(this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == opponentColor )
					{
						checkI += 1;
						checkJ += 1;
						if (!(isInBorderX(checkI) && isInBorderY(checkJ))) {
							break;
						}
						if (this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == color) {
							{
								status = PlayStatus.OK;
								//Go back and flip color
								checkI -= 1;
								checkJ -= 1;
								while(checkJ!=jTo)
								{
									
									this.disks[checkI][checkJ].setColor(color);
									checkI -= 1;
									checkJ -= 1;
								}
								
							}
						}
					}
				}
				
				//South Path
				checkI= iTo + 1 ;
				checkJ = jTo;
		
				if(isInBorderX(checkI))
				{
					while(this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == opponentColor )
					{
						checkI += 1;
						if (!(isInBorderX(checkI))) {
							break;
						}
						if (this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == color) {
							{
								status = PlayStatus.OK;
								//Go back and flip color
								checkI -= 1;
								while(checkI != iTo){
									
									this.disks[checkI][checkJ].setColor(color);
									checkI -= 1;
								}
								
							}
						}
					}
				}
				
				
				//South West Path
				checkI= iTo + 1 ;
				checkJ= jTo -1 ;
		
				if(isInBorderX(checkI) && isInBorderY(checkJ))
				{
					while(this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == opponentColor )
					{
						checkI += 1;
						checkJ-=1;
						if (!(isInBorderX(checkI) && isInBorderY(checkJ))) {
							break;
						}
						if (this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == color) {
							{
								status = PlayStatus.OK;
								//Go back and flip color
								checkI -= 1;
								checkJ+=1;
								while(checkI != iTo){
									
									this.disks[checkI][checkJ].setColor(color);
									checkI -= 1;
									checkJ+=1;
								}
								
							}
						}
					}
				}
				
				//West Path
				checkI = iTo;
				checkJ= jTo -1 ;
		
				if(isInBorderY(checkJ))
				{
					while(this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == opponentColor )
					{
						checkJ-=1;
						if (!(isInBorderY(checkJ))) {
							break;
						}
						if (this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == color) {
							{
								status = PlayStatus.OK;
								//Go back and flip color
								checkJ+=1;
								while(checkJ != jTo){
									
									this.disks[checkI][checkJ].setColor(color);
									checkJ+=1;
								}
								
							}
						}
					}
				}
				
				//North West Path
				checkI = iTo -1;
				checkJ= jTo -1 ;
		
				if(isInBorderX(checkI) && isInBorderY(checkJ))
				{
					while(this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == opponentColor )
					{
						checkI-=1;
						checkJ-=1;
						if(!(isInBorderX(checkI) && isInBorderY(checkJ))) {
							break;
						}
						if (this.disks[checkI][checkJ] != null && this.disks[checkI][checkJ].getColor() == color) {
							{
								status = PlayStatus.OK;
								//Go back and flip color
								checkI+=1;
								checkJ+=1;
								while(checkJ != jTo){
									
									this.disks[checkI][checkJ].setColor(color);
									checkI+=1;
									checkJ+=1;
								}
								
							}
						}
					}
				}
		
				
				
		if(status == PlayStatus.OK)
		{
			//add disk
			this.numDisks++;
			this.disks[iTo][jTo] = new Disk(color);
		}
		return status;
		
		
		
	}

	private boolean isInBorderX(int i) {
		if (i == 0 || i == 9)
			return false;
		return true;

	}

	private boolean isInBorderY(int j) {
		if (j == 0 || j == 9)
			return false;
		return true;

	}

	public Winner getWinner()
	{
		int numWhite=0, numBlack=0;
		for(int i=1;i<=8;i++)
		{
			for(int j=1;j<=8;j++)
			{
				if(this.disks[i][j]!= null)
				{
					if(this.disks[i][j].getColor() == Color.WHITE)
						numWhite++;
					else
						numBlack++;
				}
			}
		}
		if(numWhite>=numBlack)
		{
			return new Winner(Color.WHITE, numWhite);
		}
		else
		{
			return new Winner(Color.BLACK, numBlack);
		}
	}
	public int getNumDisks() {
		return numDisks;
	}

	public void setNumDisks(int numDisks) {
		this.numDisks = numDisks;
	}
	public void displayBoard()
	{
		String board= "  ";
		for(int j=1;j<=8;j++)
		{
			board +=j +" ";
		}
		board += "\n";
		for(int i=1;i<=8;i++)
		{
			board += i + " ";
			for(int j=1;j<=8;j++)
			{
				if(this.disks[i][j] == null)
					board +="- ";
				else if(this.disks[i][j].getColor() == Color.WHITE)
					board +="W ";
				else
					board +="B ";
			}
			board += "\n";
		}
		System.out.println(board);
	}
	

}
