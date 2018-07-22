
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class Animation extends Applet implements Runnable
{
	Thread animator;
	Image floppy;
	int imageFrame;
	boolean stopped = false;

	public void init()
	{
		setBackground(Color.red);
		setForeground(Color.yellow);

		addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				toggleAnimation();
			}
		});
	}

    public void start()
    {
		animator = new Thread(this);
		animator.start();
    }

	public void run()
	{
		while (Thread.currentThread() == animator)
		{
			repaint();

			showStatus("Click on applet to stop.");
			
			try
			{
				Thread.sleep(100);
				
				synchronized(this)
				{
					while (stopped)
					{
						showStatus("Click on applet to restart.");
						wait();
					}
				}
			}

			catch (InterruptedException e)
			{
				break;
			}

			imageFrame = (++imageFrame) % 8;
			floppy = getImage(getCodeBase(), "Image/Floppy" + imageFrame + ".jpg");
		}
	}

    public void stop()
    {
		animator = null;
		floppy = null;
	}

    public void update(Graphics g)
    {
    	try
    	{
			g.drawString("Walking Floppy", 115, 20);
			g.drawImage(floppy, 50, 25, this);
			g.drawString("Hello!", 140, 240);
		}
		
		catch (Exception ex)
		{
		}
    }

	public void paint(Graphics g)
	{
		update(g);
	}

	private synchronized void toggleAnimation()
	{
		if (animator != null && animator.isAlive())
		{
			stopped = !stopped;
			
			if (!stopped)
			{
				notify();
			}
		}
		
		else
		{
			stopped = false;
			start();
		}
	}
}

