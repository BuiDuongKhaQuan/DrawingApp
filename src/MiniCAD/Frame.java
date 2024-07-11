package MiniCAD;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class Frame extends JFrame  {
	

	 JButton backgroundColor 	= new JButton("BackgroundColor");
	 JButton Square 			= new JButton("Square");
	 JButton Circle				= new JButton("Circle");
	 JButton Ellipse			= new JButton("Ellipse");
	 JButton rectangle			= new JButton("Rectangle");
	 JButton triangle			= new JButton("Triangle");
	 JButton line				= new JButton("Line");
	 JButton delete				= new JButton("Delete");
	 JButton colorChooser		= new JButton("ColorChooser");
	 JButton move				= new JButton("Move");
	 JButton resize				= new JButton("Resize");
	 JButton changeC			= new JButton("ChangeColor");
	 JButton clear				= new JButton("Clear");
	 JButton selectedColor		= new JButton();
	 
	 JButton btWhite 			= new JButton();
	 JButton btBlack			= new JButton();
	 JButton btGray 			= new JButton();
	 JButton btBlue				= new JButton();
	 JButton btRed				= new JButton();
	 JButton btPink 			= new JButton();
	 JButton btYellow 			= new JButton();
	 JButton btGreen 			= new JButton();
	 JButton btLightGray 		= new JButton();
	 JButton btCyan				= new JButton();
	 JButton btMagenta			= new JButton();
	 JButton btOrange			= new JButton();
	 
	 Color UNSELECTED_COLOR	 	= new Color(150, 150, 150);
	 
	 ArrayList<JButton> colorButtons 	= new ArrayList<JButton>();
	 ArrayList<JButton> allButtons		= new ArrayList<JButton>();

	 JRadioButton filled 	= new JRadioButton("Filled");
	 JRadioButton border 	= new JRadioButton("Border");	 
	
	 JMenuBar bar 			= new JMenuBar();
	 
	 JMenu file 			= new JMenu("File");
	 JMenu edit 			= new JMenu("Edit");
	 JMenu about		= new JMenu("About");
	 
	 JMenuItem New 			= new JMenuItem("New");
	 JMenuItem save			= new JMenuItem("Save");
	 JMenuItem open 		= new JMenuItem("Open");
	 JMenuItem exit			= new JMenuItem("Exit");
	 JMenuItem undo  		= new JMenuItem("Undo");
	 JMenuItem redo 		= new JMenuItem("Redo");
	
	 
	 JPanel drawingPanel	= new JPanel();
	 JPanel controlPanel 	= new JPanel();
	 JPanel sidePanel		= new JPanel();
	 JPanel colorButton		= new JPanel();
	 JPanel colorPanel		= new JPanel();
	 JPanel FBC 			= new JPanel();
	 
	 Paint paint 		= new Paint();;
	 
	 JFileChooser SLfile;
	 
	 XmlFile xf 			= new XmlFile();
	 
	 boolean PressedUndo 	= false;
	 static String url 		= "";

	public class StatusBar extends JPanel {
		 JLabel Shape 			= new JLabel("Drawing mode:______", JLabel.CENTER);
		 JLabel location     	= new JLabel("Mouse Coordinates  X: 0  Y: 0", JLabel.CENTER);
		 JLabel Control     	= new JLabel("Control model:______", JLabel.CENTER);
		 JLabel Color     		= new JLabel("Color model: ", JLabel.CENTER);
		 JLabel T				= new JLabel("                               "
		 		+ "                                           ", JLabel.CENTER);
		 JLabel TT				= new JLabel("                                                "
			 		+ "                                           ", JLabel.CENTER);
		 JLabel TTT				= new JLabel("                               "
			 		+ "                                           ", JLabel.CENTER);

		 
		 String ShapeType ;
		 String ControlType ;	 
		 
		public StatusBar() {
			
			add(Shape);
			add(T);
			add(Color);
			add(selectedColor);
			add(TT);
			add(Control);
			add(TTT);
		    add(location);		    
		    
		  
		    
		    selectedColor.setPreferredSize(new Dimension(22,22));
			selectedColor.setMaximumSize(new Dimension(20,20));
				
		    
			allButtons.addAll(new ArrayList<JButton>(Arrays.asList(
					Ellipse, rectangle, triangle, line, Circle, Square, resize
					,move ,delete, changeC )));			
			
			colorButtons.addAll(new ArrayList<JButton>(Arrays.asList(btWhite, 
							btBlack, btGray, btBlue, btRed,
							btPink, btYellow, btCyan, 
							btMagenta, btOrange, btGreen, btLightGray)));
			
			for (int i = 0; i < colorButtons.size(); i++) {
				final int btIndex = i;
				colorButtons.get(i).setBorder(BorderFactory.createRaisedBevelBorder());
				colorButtons.get(i).setPreferredSize(new Dimension(25,25));
				colorButtons.get(i).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Color tmp = colorButtons.get(btIndex).getBackground();
						toggleColorButtons(colorButtons, colorButtons.get(btIndex));
							paint.setColor(tmp);
							selectedColor.setBackground(tmp);						
					}
				});
			}
			for (int i =0; i <allButtons.size() ; i++)	{
				allButtons.get(i).setBorder(BorderFactory.createRaisedBevelBorder());
				allButtons.get(i).setPreferredSize(new Dimension(95,25));
				allButtons.get(i).setForeground(UNSELECTED_COLOR);
				allButtons.get(i).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						toggleTools(allButtons, allButtons.get(paint.flag-1));						
						String actionCommand = evt.getActionCommand();
						if(actionCommand.equals("Square")){
							setShape(actionCommand);						
						    }
						else if (actionCommand.equals("Circle")){
							setShape(actionCommand);					
						    }
						else if (actionCommand.equals("Ellipse")){
							setShape(actionCommand);
						    }
						else if (actionCommand.equals("Rectangle")){
							setShape(actionCommand);
						    }
						else if (actionCommand.equals("Triangle")){
							setShape(actionCommand);
						    }
						else if (actionCommand.equals("Line")){
							setShape(actionCommand);
						    }	
						else if (actionCommand.equals("Delete")){
							setControl(actionCommand);
						    }
						else if (actionCommand.equals("Move")){
							setControl(actionCommand);
						    }
						else if (actionCommand.equals("Resize")){
							setControl(actionCommand);
						    }
						else if (actionCommand.equals("ChangeColor")){
							setControl(actionCommand);
						    }
						else if (actionCommand.equals("Clear")){
							setControl(actionCommand);
						    }
					}
				});		
			}
			  
		    MouseAdapter adapter = new MouseAdapter() {
				 public void mouseMoved(MouseEvent event) {
					 location.setText(String.format("Mouse Coordinates  X: %d  Y: %d", 
			            		event.getX(), event.getY()));
			     }
				 public void mouseDragged(MouseEvent event) {
					 location.setText(String.format("Mouse Coordinates  X: %d  Y: %d", 
			            		event.getX(), event.getY()));
				 }
			};
			
			paint.addMouseListener(adapter);
			paint.addMouseMotionListener(adapter);
			
			
		}
		
		public void setShape(String Type)
		{
			ShapeType = Type;
			Shape.setText("Drawing mode: " + ShapeType);
		}
		public void setControl(String Type1)
		{
			ControlType = Type1;
			Control.setText("Control mode: " + ControlType);
		}
		
		
	}
	public void toggleTools(ArrayList<JButton> o, JButton selected) {
		for (int i = 0; i < o.size(); i++) {
			if (o.get(i) == selected) {
				o.get(i).setForeground(Color.BLACK);
			} else {
				o.get(i).setForeground(UNSELECTED_COLOR);
			}
		}
	}
	public void toggleColorButtons(ArrayList<JButton> buttons, JButton selected) {
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i) == selected) {
				buttons.get(i).setBorder(BorderFactory.createLoweredBevelBorder());
			} else {
				buttons.get(i).setBorder(BorderFactory.createRaisedBevelBorder());
			}
		}
	}
	

	public Frame() {
		
		super("Mini CAD");
			
		add(paint, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.NORTH);
		getContentPane().add(new StatusBar(), BorderLayout.SOUTH);

		setSize(1500, 900);
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(bar);
		
		
		
		
		btWhite.setBackground(Color.WHITE);
		btBlack.setBackground(Color.BLACK);
		btGray.setBackground(Color.GRAY);
		btBlue.setBackground(Color.BLUE);
		btRed.setBackground(Color.RED);
		btPink.setBackground(Color.PINK);
		btYellow.setBackground(Color.YELLOW);
		btCyan.setBackground(Color.CYAN);
		btMagenta.setBackground(Color.MAGENTA);
		btOrange.setBackground(Color.ORANGE);
		btGreen.setBackground(Color.GREEN);
		btLightGray.setBackground(Color.LIGHT_GRAY);
		selectedColor.setBackground(Color.BLACK);
		
		colorButton.setLayout(new GridLayout(2,6,3,3));
		drawingPanel.setLayout(new GridLayout(2,3,3,3));
		controlPanel.setLayout(new GridLayout(2,2,3,3));		
		
		colorButton.add(btWhite);
		colorButton.add(btBlack);
		colorButton.add(btGray);
		colorButton.add(btBlue);
		colorButton.add(btRed);
		colorButton.add(btPink);
		colorButton.add(btYellow);
		colorButton.add(btCyan);
		colorButton.add(btMagenta);
		colorButton.add(btOrange);
		colorButton.add(btGreen);
		colorButton.add(btLightGray);

		drawingPanel.add(line);
		drawingPanel.add(Square);
		drawingPanel.add(rectangle);
		drawingPanel.add(Circle);
		drawingPanel.add(Ellipse);	
		drawingPanel.add(triangle);
				
		controlPanel.add(move);
		controlPanel.add(resize);
		controlPanel.add(changeC);
		controlPanel.add(delete);
				
		colorPanel.add(backgroundColor);			
		colorPanel.add(colorChooser);			
		colorPanel.add(colorButton);
		
		FBC.add(filled);
		FBC.add(border);
		FBC.add(clear);
	
		sidePanel.setBorder(BorderFactory.createEtchedBorder());
		sidePanel.add(colorPanel);
		sidePanel.add(controlPanel);
		sidePanel.add(drawingPanel);	
		sidePanel.add(FBC);
		
		
		colorChooser.setBorder(BorderFactory.createRaisedBevelBorder());
		colorChooser.setPreferredSize(new Dimension(115,30));
		
		backgroundColor.setBorder(BorderFactory.createRaisedBevelBorder());
		backgroundColor.setPreferredSize(new Dimension(115,30));
		
		clear.setBorder(BorderFactory.createRaisedBevelBorder());
		clear.setPreferredSize(new Dimension(100,30));
				
		
		drawingPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLoweredBevelBorder(),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)));
		controlPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLoweredBevelBorder(),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)));
		colorPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLoweredBevelBorder(),
				BorderFactory.createEmptyBorder(0, 8, 0, 8)));
		FBC.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLoweredBevelBorder(),
				BorderFactory.createEmptyBorder(12, 8, 11, 8)));
		
		
		edit.add(undo);
		edit.add(redo);
		
		file.add(New);
		file.add(open);
		file.add(save);	
		file.add(exit);
		
		bar.add(file);
		bar.add(edit);
		bar.add(about);
		
		Action newAction = new AbstractAction("New") {			
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.shapes.clear();
				paint.setColor2(Color.WHITE);;
				repaint();	
			}
		};
		newAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		newAction.putValue(Action.ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK|KeyEvent.SHIFT_DOWN_MASK));
		New.setAction(newAction);
		
		
		Action openAction = new AbstractAction("Open") {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int retval = 0;
				OpenFile(retval);
				if (retval == JFileChooser.APPROVE_OPTION) {
					url = "" + SLfile.getSelectedFile();
					try {
						xf.load(paint.shapes, paint.currentShapes,
								paint.ur.undo, paint.ur.lastaction);
						paint.repaint();
					} catch (Exception e1) {
					}
				}			
			}
		};
		openAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
		openAction.putValue(Action.ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		open.setAction(openAction);
		
			
		Action saveAction = new AbstractAction("Save") {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int retval = 0;
				FileChooserSave(retval);
				if (retval == JFileChooser.APPROVE_OPTION) {
					url = "" + SLfile.getSelectedFile();
					try {
						xf.save(paint.currentShapes);
					} catch (Exception e1) {
				}
				}			
			}
		};
		saveAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
		saveAction.putValue(Action.ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		save.setAction(saveAction);
		
		
		exit.setMnemonic(KeyEvent.VK_E);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, 
	                                ActionEvent.CTRL_MASK|KeyEvent.SHIFT_DOWN_MASK));
		exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0); 
            }
        });
		
		Action undoAction = new AbstractAction("Undo") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (paint.ur.undo.isEmpty()) {
					} else {
						PressedUndo = true;
						paint.ur.undo(paint.currentShapes);
						paint.repaint();
					}
				} catch (Exception e2) {
			  }		
			}
		};
		undoAction.putValue(Action.ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		undo.setAction(undoAction);
		
		
		Action redoAction = new AbstractAction("Redo") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (PressedUndo == true && !paint.ur.redo.isEmpty()
						&&!paint.ur.undo.isEmpty()) {
					paint.ur.redo(paint.currentShapes);
					paint.repaint();
				} else {
			  }			
			}
		};
		redoAction.putValue(Action.ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
		redo.setAction(redoAction);
			
		about.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                 System.out.println("kkkkkk");
                 new About();
            }
        });
		
		Ellipse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 1;
				PressedUndo = false;
			}
		});
		
		rectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 2;
				PressedUndo = false;
			}
		});
		
		triangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 3;
				PressedUndo = false;
			}
		});

		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 4;
				PressedUndo = false;
			}
		});
		
		Circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 5;
				PressedUndo = false;
			}
		});
		
		Square.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 6;
				PressedUndo = false;
			}
		});


		resize.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				paint.flag = 7;
				PressedUndo = false;

			}
		});

		move.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				paint.flag = 8;
				PressedUndo = false;
			}
		});
		
		colorChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.color = JColorChooser.showDialog(paint, "Choose a color",
						paint.color);
				
				selectedColor.setBackground(paint.color);
				
				PressedUndo = false;
			}
		});
		backgroundColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.color2 = JColorChooser.showDialog(paint, "Choose a color",
						paint.color2);
				PressedUndo = false;
				repaint();
			}
		});
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.shapes.clear();
				repaint();
			}
		});

		
		changeC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 10;
				PressedUndo = false;
			}
		});

		filled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.isFilled = true;
				PressedUndo = false;
				border.setSelected(false);
			}
		});

		border.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.isFilled = false;
				PressedUndo = false;
				filled.setSelected(false);
			}
		});

		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PressedUndo = false;
				paint.flag = 9;
			}
		});

	}

	private void OpenFile(int retval) {
		SLfile = new JFileChooser();
		retval = SLfile.showOpenDialog(null);
		SLfile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		SLfile.setBackground(Color.white);
	}

	private void FileChooserSave(int retval) {
		SLfile = new JFileChooser();
		retval = SLfile.showSaveDialog(null);
		SLfile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		SLfile.setBackground(Color.white);
	}

	public static void main(String[] args) {
		try {
			Frame f = new Frame();
			f.setVisible(true);
		} catch (Exception e) {
			System.out.println("unexpected error");
		}
	}
}
