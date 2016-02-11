class Priority_Queue
{
	private int MaxSize;
	private int CurrentSize = 0;
	VertexSP [] VertexSP = null;

	Priority_Queue(int MaxSize)
	{
		this.MaxSize			  =  MaxSize;
		VertexSP				  =  new VertexSP[ MaxSize + 1 ];

		VertexSP[0] = new VertexSP(-1, false, -1, Double.NEGATIVE_INFINITY);
	}

	private void PercolateDown( int Hole)
	{
		int Child;
		VertexSP tmp = VertexSP [ Hole ];

		for ( ; Hole * 2 <= CurrentSize; Hole = Child)
		{
			Child = Hole * 2;
			if (Child != CurrentSize && VertexSP[ Child + 1 ].dv < VertexSP[ Child ].dv )
				Child++;

			if (VertexSP[ Child ].dv < tmp.dv )
				VertexSP [ Hole ] = VertexSP [ Child ];
			else
				break;
		}

		VertexSP [ Hole ] = tmp;
	}

	public void enqueue(VertexSP X)
		throws Priority_QueueError
	{
		if (isFull() )
			throw new Priority_QueueError("PQ enqueue() cannot enqueue new item because the queue is full.");

		int Hole = ++CurrentSize;
		//Percolate Hole Up
		for ( ; X.dv  < VertexSP[ Hole / 2 ].dv; Hole /=2)
			VertexSP[ Hole ] = VertexSP [ Hole / 2 ];

		VertexSP[ Hole ] = X;
	}

	public VertexSP FindMin()
		throws Priority_QueueError
	{
		if (isEmpty() )
			throw new Priority_QueueError("FindMin() failed because Priority Queue is empty.");

		return VertexSP[1];

	}

	public VertexSP dequeue()
		throws Priority_QueueError
	{
		if (isEmpty() )
			throw new Priority_QueueError("dequeue() failed because Priority Queue is empty.");

		VertexSP tmp = VertexSP [ 1 ];
		VertexSP [ 1 ] = VertexSP [ CurrentSize--];
		PercolateDown(1);

		return tmp;
	}

	public boolean isEmpty()
	{
		return CurrentSize == 0;
	}

	public boolean isFull()
	{
		return MaxSize == CurrentSize;
	}

	public void MakeEmpty()
	{
		CurrentSize = 0;
	}

}//End of Priority_Queue


class Priority_QueueError extends Exception
{
	String Error_Message;
	Priority_QueueError(String error_message)
	{
		Error_Message = error_message;
	}
}


class testPriority_Queue
{
	public static void main(String [] args)
	{
		Priority_Queue myPQ = new Priority_Queue(10);


		VertexSP X1 = new VertexSP(1, false, -1, 4545.0);
		VertexSP X2 = new VertexSP(2, false, -1, -4545.0);
		VertexSP X3 = new VertexSP(3, false, -1, 245.0);
		VertexSP X4 = new VertexSP(4, false, -1, -8545.0);
		VertexSP X5 = new VertexSP(5, false, -1, 545.0);



		try
		{
			myPQ.enqueue(X1);
			myPQ.enqueue(X2);
			myPQ.enqueue(X3);
			myPQ.enqueue(X4);
			myPQ.enqueue(X5);
		}
		catch (Priority_QueueError e )
		{
			System.out.println("\n" + e.Error_Message);
		}

		try
		{
			VertexSP min = myPQ.FindMin();
			System.out.println("min.Vertex_id \t\t" + min.vertex_id);
			System.out.println("min.dv \t\t\t" + min.dv);
		}
		catch (Priority_QueueError e )
		{
			System.out.println("\n" + e.Error_Message);
		}
	}

}
