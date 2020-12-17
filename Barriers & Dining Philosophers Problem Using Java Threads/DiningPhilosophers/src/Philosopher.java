import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Thread.*; // I need this.

class Table
{
	// FOR GUI:
	static JFrame frame;
	private JLabel[] plates = new JLabel[5];
	private JLabel[] forks = new JLabel[5];
	static Table window;
	static BufferedImage fork;

	/**
	 * Launch the application.

	 /**
	 * Create the application.
	 */

	public Table()
	{
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		BufferedImage plate = null;
		try {
			plate = ImageIO.read(new File("spaghetti_yellow.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.getContentPane().setLayout(null);
		plates[0] = new JLabel(new ImageIcon(plate));
		plates[0].setBounds(185, 10, 100, 100);
		frame.getContentPane().add(plates[0]);
		plates[0].setVisible(false);

		plates[1] = new JLabel(new ImageIcon(plate));
		plates[1].setBounds(300, 100, 100, 100);
		frame.getContentPane().add(plates[1]);
		plates[1].setVisible(false);

		plates[2] = new JLabel(new ImageIcon(plate));
		plates[2].setBounds(230, 230, 100, 100);
		frame.getContentPane().add(plates[2]);
		plates[2].setVisible(false);

		plates[3] = new JLabel(new ImageIcon(plate));
		plates[3].setBounds(70, 210, 100, 100);
		frame.getContentPane().add(plates[3]);
		plates[3].setVisible(false);

		plates[4] = new JLabel(new ImageIcon(plate));
		plates[4].setBounds(50, 80, 100, 100);
		frame.getContentPane().add(plates[4]);
		plates[4].setVisible(false);

		try {
			fork = ImageIO.read(new File("fork_white_1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[0] = new JLabel(new ImageIcon(fork));
		forks[0].setBounds(250, 40, 100, 100);
		frame.getContentPane().add(forks[0]);

		try {
			fork = ImageIO.read(new File("fork_white_2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[1] = new JLabel(new ImageIcon(fork));
		forks[1].setBounds(280, 170, 100, 100);
		frame.getContentPane().add(forks[1]);

		try {
			fork = ImageIO.read(new File("fork_white_3.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[2] = new JLabel(new ImageIcon(fork));
		forks[2].setBounds(150, 220, 100, 100);
		frame.getContentPane().add(forks[2]);

		try {
			fork = ImageIO.read(new File("fork_white_4.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[3] = new JLabel(new ImageIcon(fork));
		forks[3].setBounds(60, 145, 100, 100);
		frame.getContentPane().add(forks[3]);

		try {
			fork = ImageIO.read(new File("fork_white_5.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[4] = new JLabel(new ImageIcon(fork));
		forks[4].setBounds(95, 15, 100, 100);
		frame.getContentPane().add(forks[4]);
	}

	public void PutPlate_GUI(int i)
	{
		plates[i].setVisible(true);
	}

	public void StartDining_GUI(int i)
	{
		try {
			plates[i].setIcon(new ImageIcon(ImageIO.read(new File("spaghetti_white.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Hungry_GUI(int i) {

		try {
			plates[i].setIcon(new ImageIcon(ImageIO.read(new File("spaghetti_red.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void ForkTake_GUI(int i) {
		try{
			if (i == 0)
			{
				forks[0].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_1.jpg"))));
				forks[4].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_5.jpg"))));
			}
			else if (i == 1)
			{
				forks[0].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_1.jpg"))));
				forks[1].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_2.jpg"))));
			}
			else if (i == 2)
			{
				forks[1].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_2.jpg"))));
				forks[2].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_3.jpg"))));
			}
			else if (i == 3)
			{
				forks[2].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_3.jpg"))));
				forks[3].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_4.jpg"))));
			}
			else if (i == 4)
			{
				forks[3].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_4.jpg"))));
				forks[4].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_5.jpg"))));
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Eating_GUI(int i) {
		try {
			plates[i].setIcon(new ImageIcon(ImageIO.read(new File("spaghetti_blue.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			sleep(3000);
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}

	public void ForkPut_GUI(int i) {
		try{
			if (i == 0)
			{
				forks[0].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_1.jpg"))));
				forks[4].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_5.jpg"))));
			}
			else if (i == 1)
			{
				forks[0].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_1.jpg"))));
				forks[1].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_2.jpg"))));
			}
			else if (i == 2)
			{
				forks[1].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_2.jpg"))));
				forks[2].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_3.jpg"))));
			}
			else if (i == 3)
			{
				forks[2].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_3.jpg"))));
				forks[3].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_4.jpg"))));
			}
			else if (i == 4)
			{
				forks[3].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_4.jpg"))));
				forks[4].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_5.jpg"))));
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void StopEating_GUI(int i)
	{
		try {
			plates[i].setIcon(new ImageIcon(ImageIO.read(new File("spaghetti_white.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class Philosopher extends Thread {

	private static Table table;

	private int THINKING = 0; // THINKING is set to 0.
	private int HUNGRY = 1; // HUNGRY is set to 1.
	private int EATING = 2; // EATING is set to 2.

	private Semaphore [] sem;
	private Semaphore[] barriers;
	private Semaphore mutex;
	private int id;

	private int [] state;
	private int N;

	public Philosopher(int i, Semaphore [] s, int [] mystate, int philnumber, Semaphore mu, Semaphore[] bar)
	{
		id = i;
		sem = s;
		state = mystate;
		N = philnumber;
		mutex = mu;
		barriers = bar;
	}

	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					table = new Table();
					table.frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		int N = 5;
		int [] state = new int [N];

		Semaphore mutex = new Semaphore (1);
		Semaphore [] semarray = new Semaphore [N];
		Semaphore [] bararray = new Semaphore [N];

		Philosopher[] oldies = new Philosopher [N];

		for (int i=0; i < N; i++)
		{
			semarray[i] = new Semaphore(0,true);
			bararray[i] = new Semaphore(0,true);
		}
		for (int i=0; i < N; i++)
		{
			oldies[i] = new Philosopher(i, semarray, state, N, mutex,bararray);
			oldies[i].start();
			try
			{
				sleep(5000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	// I implemented an algorithm so that no philosopher starves and they are never deadlocked.
	// I made use of semaphores and mutexes in order to achieve this.
	int right(int a){
		a=(1+id) % N; // An easy calculation.
		return a;}

	int left(int b){
		b = (-1 +id+ N) % N; // An easy calculation.
		return b;}

	void test(int i) // Thanks to the Recitation5 slides.
	{
		if(state[left(i)] != EATING &&state[right(i)] != EATING&&state[i] == HUNGRY) // left and right are declared and defined above.
		{
			// Conditions are satisfied.
			state[i]=EATING;
			sem[i].release(); // up(&s[i]);
		}
	}

	void take_forks(int i) // Thanks to the Recitation5 slides.
	{
		try
		{
			mutex.acquire(); // down(&mutex); Entering critical region.
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		state[i] = HUNGRY; // Corresponding philosopher is HUNGRY at the moment.
		test(i); // Testing whether philosopher can acquire 2 forks or can not.
		mutex.release(); // Exiting critical region. up(&mutex);
		try
		{
			sem[i].acquire(); // Block if forks weren't acquired.
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	void put_forks(int i) // Thanks to the Recitation5 slides.
	{
		try
		{
			mutex.acquire(); // down(&mutex);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		state[i] = THINKING; // If philosopher puts forks, it means that the his next phase will be THINKING.
		test(left(i)); // Testing left.
		test(right(i)); // Testing right.
		mutex.release(); // Exiting the critical region. up(&mutex);
	}

	void think()
	{
		int durationOfThinking = ThreadLocalRandom.current().nextInt(0,10001); // durationOfThinking is a random variable which will be between 0-10.
		state[id] = THINKING; // Since this is a function for thinking, it sets to THINKING.
		try
		{
			sleep(durationOfThinking); // durationOfThinking is passed as an argument.
		}
		catch (InterruptedException e) // Exception handling by me.
		{
			e.printStackTrace();
		}

		table.Hungry_GUI(id);
	}

	void Eat() // These function returns nothing. It lets GUI functions work.
	{
		table.ForkTake_GUI(id); // GUI
		table.Eating_GUI(id); // GUI
		table.StopEating_GUI(id); // GUI
		table.ForkPut_GUI(id); // GUI
	}

	void Barrier() // I have implemented a barrier which is for 5 threads.
	{
		try
		{
			int c;

			for(c = 0;c< N-1;c++){
				barriers[id].release(); // up
			}

			for(c =0; c< N;c++){
				if(!(id == c))
				{
					barriers[c].acquire(); // down
				}	}
		}
		catch (InterruptedException e) // Exception handling by me.
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		try
		{
			int durationOfWaiting = ThreadLocalRandom.current().nextInt(1000,10001); // I need a random variable which is between 1-10.
			sleep(durationOfWaiting); // I call sleep function with a random variable as an argument.
		}
		catch (InterruptedException e) // Exception handling by me.
		{
			e.printStackTrace();
		}

		table.PutPlate_GUI(id); // First of all I have to put plates on the table and this is the GUI function that puts plates on the table visually.

		// Barrier
		Barrier(); // I have implemented a barrier for 5 threads and this is a call.

		// Dining
		table.StartDining_GUI(id); // GUI function that starts dining visually. They are starting dining.

		boolean condition=true;
		while(condition) // Thanks to the Recitation5 slides, this will repeat forever.
		{
			think(); // philosopher is thinking.
			take_forks(id); // acquiring 2 forks or block.
			Eat(); // Eat function will work.
			put_forks(id); // put both forks back on table.
		}
	}
}