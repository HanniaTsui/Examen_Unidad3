import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Desktop;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;

public class ControlEscolar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelMenu, panelMenuVertical, panelSur, panel;
	private JTextField nombres, textID;
	private JPasswordField password;
	private JLabel logo, castillo, logoReg1, logoReg2, logoPerfil, logoEdit2, logoEdit1, logoCon1, logoCon2, logoElim2A, logoElim1; 
	private JLabel labelBienv, labelEstudiante,labelDocente,labelGris, labelGris_1, labelGris_2, labelGris_3, labelGris_4, labelGris_5, labelGris_6, labelGris_7;
	private JButton btn_1, btn_2, btn_3,  btn_4, btn_5, btn_6, btn_7, btnRegistrarNuevo, btnBuscar, btnConsNuevo, btnDescargar;
	private boolean sesionIniciada=false, actualizado=false;
	private JTextField textNombres, textApellidos,textFecha, textEmail, textPais, textTel, textAño, textCasa;
	private JTextField casa,anio, nombre, apellido, fecha, pais, tel, email, comboGenero;
	String nuevoNombre, nuevoApellido, nuevoEmail, nuevoTel, nuevaFecha, nuevoPais, nuevaCasa, nuevoGenero; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlEscolar frame = new ControlEscolar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ControlEscolar() {
		setTitle("Sistema de Control Escolar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1000,700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
	//	inicioSesion();
	//	menuPrincipal();
		editarAlumno();
	//	consultarAlumno();
	}
	
	public void menu() { // Inicio - Cerrar Sesion
		contentPane.setLayout(new BorderLayout(0, 0));
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(199, 146, 66));
		panelMenu.setSize(new Dimension(1000, 15));
		contentPane.add(panelMenu, BorderLayout.NORTH);
		panelMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JMenuBar menu = new JMenuBar();
		menu.setOpaque(false);
		menu.setBorder(null);
		menu.setBounds(0, 0, 240, 22);
		panelMenu.add(menu);
		
		JMenuItem item1 = new JMenuItem("Inicio");
		item1.setHorizontalAlignment(SwingConstants.CENTER);
		item1.setOpaque(false);
		item1.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        inicioSesion();
		        if(sesionIniciada)
		        {
		        	nombres.setText("Yo");
		        	password.setText("123");
		        }
        	}
        });
		menu.add(item1);
		
		
		JMenuItem item2 = new JMenuItem("Cerrar sesión");
		item2.setHorizontalAlignment(SwingConstants.CENTER);
		item2.setOpaque(false);
		item2.addActionListener(new ActionListener()
		{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        inicioSesion();
        	}
        });
		menu.add(item2);		
	}
	
	public void inicioSesion() 
	{
		menuI_R_A();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelMenu, BorderLayout.NORTH);	

		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel labelID = new JLabel("ID DE USUARIO",0);
		labelID.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelID.setBounds(91,207, 380, 39);
		panel.add(labelID);
		
		JLabel labelPS = new JLabel("CONTRASEÑA", SwingConstants.CENTER);
		labelPS.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelPS.setBounds(91, 346, 380, 39);
		panel.add(labelPS);
		
		password = new JPasswordField();
		password.setColumns(10);
		password.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		password.setBounds(131, 404, 300, 40);
		panel.add(password);
		
		nombres = new JTextField();
		nombres.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		nombres.setBounds(131, 265, 300, 40);
		panel.add(nombres);
		nombres.setColumns(10);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.setBorderPainted(false);
		btnAcceder.setForeground(new Color(255, 255, 255));
		btnAcceder.setBackground(new Color(130, 36, 55));
		btnAcceder.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		btnAcceder.setBorder(new LineBorder(new Color(0, 189, 189), 5));
		btnAcceder.setBounds(221, 488, 120, 30);
		btnAcceder.setFocusable(false);
		btnAcceder.addActionListener(new ActionListener()
		{
        	public void actionPerformed(ActionEvent e) {
        		String usr = nombres.getText();
				String pwd = new String (password.getPassword());
				if (usr.length()<=0){
					nombres.setBorder(BorderFactory.createLineBorder(Color.red,3));
				} else {
					 nombres.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
				}
				if(pwd.length()<=0) {
					password.setBorder(BorderFactory.createLineBorder(Color.red,3));
				} else {
					 password.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
				}
				
				if(usr.equals("Yo")) {
					if(pwd.equals("123")) 	{
						sesionIniciada=true;
						contentPane.removeAll();
				        contentPane.revalidate();
				        contentPane.repaint();
						menuPrincipal(); //Menu de docentes - alumnos
					}
					else {
		                JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.WARNING_MESSAGE);
		            }
				}else {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado","Error", JOptionPane.WARNING_MESSAGE);
				}
                
			}});      
		panel.add(btnAcceder);		
		
		JLabel labelGris = new JLabel("");
		labelGris.setOpaque(true);
		labelGris.setBackground(new Color(208, 205, 193));
		labelGris.setBounds(91, 188,380,355);
		panel.add(labelGris);
		
		castillo = new JLabel();
		castillo.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(199, 146, 66), new Color(199, 146, 66), new Color(199, 146, 66), new Color(199, 146, 66)));
		castillo.setIcon(new ImageIcon(getClass().getResource("castillo (1).jpg")));
		castillo.setBounds(542,188,350,290);
		panel.add(castillo);		
		
		JButton btnNoticias = new JButton("Noticias");
		btnNoticias.setForeground(Color.WHITE);
		btnNoticias.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		btnNoticias.setFocusable(false);
		btnNoticias.setBackground(new Color(130, 36, 55));
		btnNoticias.setBounds(658, 495, 120, 30);
		btnNoticias.addActionListener(new ActionListener()
		{
        	public void actionPerformed(ActionEvent e) {
        		try {
        	        Desktop.getDesktop().browse(new URI("https://harrypotter.fandom.com/es/wiki/Art%C3%ADculos_de_El_Profeta"));
        	    } catch (IOException | URISyntaxException ep) {
        	        ep.printStackTrace();
        	    }
        }	
		});
		panel.add(btnNoticias);
		elementos();		
	}
	
	public void menuI_R_A() { //Menu inicio, recuperar, acceder
		contentPane.setLayout(new BorderLayout(0, 0));
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(199, 146, 66));
		contentPane.add(panelMenu, BorderLayout.NORTH);
		panelMenu.setLayout(new FlowLayout(FlowLayout.LEFT));		
		
		JMenuBar menu = new JMenuBar();
		menu.setOpaque(false);
		menu.setBorder(null);
		menu.setBounds(0, 0, 240, 22);
		panelMenu.add(menu);
		
		JMenuItem item1 = new JMenuItem("Inicio");
		item1.setOpaque(false);
		item1.setHorizontalAlignment(SwingConstants.CENTER);
		item1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        inicioSesion();
		        if(sesionIniciada)  {
		        	nombres.setText("Yo");
		        	password.setText("123");
		        } 	
        	}
        });
		menu.add(item1);
		
		JMenuItem item2 = new JMenuItem("Recuperar contraseña");
		item2.setHorizontalAlignment(SwingConstants.CENTER);
		item2.setOpaque(false);
		item2.addActionListener(new ActionListener()		{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        recuperar();
        	}
        });
		menu.add(item2);
				
		JMenuItem item3 = new JMenuItem("¿Cómo acceder al sistema?");
		item3.setHorizontalAlignment(SwingConstants.CENTER);
		item3.setOpaque(false);
		item3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        comoAcceder();
        	}
        });
		menu.add(item3);
		
	}
	
	public void menuVertical()
	{
		menu();
		panelMenuVertical = new JPanel();
		panelMenuVertical.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(199, 146, 66)), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		panelMenuVertical.setBackground(new Color(82, 24, 37));
		contentPane.add(panelMenuVertical, BorderLayout.WEST);
		panelMenuVertical.setSize(50,600);
		panelMenuVertical.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setBackground(new Color(82, 24, 37));
		menuBar.setLayout(new GridLayout(0, 1)); 
		panelMenuVertical.add(menuBar);
		
		Component verticalStrut = Box.createVerticalStrut(70); verticalStrut.setEnabled(false); menuBar.add(verticalStrut);
		
		JMenu menuEstudiantes = new JMenu("Estudiantes  >");
		menuEstudiantes.setBorder(null);
		menuEstudiantes.setForeground(new Color(255, 255, 255));
		menuBar.add(menuEstudiantes);
		
		JMenuItem itemE_1 = new JMenuItem("Registrar estudiante");
		itemE_1.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        registrarAlumno();
        	}
        });
		menuEstudiantes.add(itemE_1);
		
		JMenuItem itemE_2 = new JMenuItem("Editar estudiante");
		itemE_2.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        editarAlumno();
        	}
        });
		menuEstudiantes.add(itemE_2);
		
		JMenuItem itemE_3 = new JMenuItem("Eliminar estudiante");
		itemE_3.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        eliminarAlumno();
        	}
        });
		menuEstudiantes.add(itemE_3);
		
		JMenuItem itemE_4 = new JMenuItem("Consultar estudiante");
		itemE_4.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        consultarAlumno();
        	}
        });
		menuEstudiantes.add(itemE_4);
		
		menuBar.add(Box.createVerticalStrut(5));

		JMenu menuDocentes = new JMenu("Docentes     >");
		menuDocentes.setBorder(null);
		menuDocentes.setForeground(new Color(255, 255, 255));
		menuBar.add(menuDocentes);
		
		JMenuItem itemD_1 = new JMenuItem("Registrar docente");
		menuDocentes.add(itemD_1);
		
		JMenuItem itemD_2 = new JMenuItem("Editar docente");
		menuDocentes.add(itemD_2);
		
		JMenuItem itemD_3 = new JMenuItem("Eliminar docente");
		menuDocentes.add(itemD_3);
		
		JMenuItem itemD_4 = new JMenuItem("Consultar docente");
		menuDocentes.add(itemD_4);
		
	}
	
	public void menuPrincipal()
	{
		menu();
		
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelMenu, BorderLayout.NORTH);

		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
		contentPane.add(panel, BorderLayout.CENTER);
		
		elementos();
		labelBienv = new JLabel("Bienvenido, ¿qué desea realizar?",0);
		labelBienv.setForeground(new Color(217, 217, 217));
		labelBienv.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelBienv.setBounds(0, 130, 996, 30);
		panel.add(labelBienv);
		
		labelEstudiante = new JLabel("ESTUDIANTE" ,0);
		labelEstudiante.setForeground(new Color(0, 0, 0));
		labelEstudiante.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelEstudiante.setOpaque(true);
		labelEstudiante.setBackground(new Color(212, 175, 55));
		labelEstudiante.setBounds(155, 188, 314, 57);
		panel.add(labelEstudiante);
		
		labelDocente = new JLabel("DOCENTE", SwingConstants.CENTER);
		labelDocente.setForeground(new Color(0, 0, 0));
		labelDocente.setOpaque(true);
		labelDocente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelDocente.setBackground(new Color(212, 175, 55));
		labelDocente.setBounds(535, 188, 314, 57);
		panel.add(labelDocente);
		
		JButton btn = new JButton("Registrar alumno");
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btn.setFocusable(false);
		btn.setBackground(new Color(130, 36, 55));
		btn.setBounds(150, 378, 140, 30);
		btn.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        registrarAlumno();
        	}
        });
		panel.add(btn);
		
		
		btn_1 = new JButton("Editar alumno");
		btn_1.setForeground(Color.WHITE);
		btn_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btn_1.setFocusable(false);
		btn_1.setBackground(new Color(130, 36, 55));
		btn_1.setBounds(334, 378, 140, 30);
		btn_1.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        editarAlumno();
        	}
        });
		panel.add(btn_1);
		
		btn_2 = new JButton("Consultar alumno");
		btn_2.setForeground(Color.WHITE);
		btn_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btn_2.setFocusable(false);
		btn_2.setBackground(new Color(130, 36, 55));
		btn_2.setBounds(150, 543, 140, 30);
		btn_2.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        consultarAlumno();
        	}
        });
		panel.add(btn_2);
		
		btn_3 = new JButton("Eliminar alumno");
		btn_3.setForeground(Color.WHITE);
		btn_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btn_3.setFocusable(false);
		btn_3.setBackground(new Color(130, 36, 55));
		btn_3.setBounds(334, 543, 140, 30);
		btn_3.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
		        eliminarAlumno();
        	}
        });
		panel.add(btn_3);
		
		btn_4 = new JButton("Registrar docente");
		btn_4.setForeground(Color.WHITE);
		btn_4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btn_4.setFocusable(false);
		btn_4.setBackground(new Color(130, 36, 55));
		btn_4.setBounds(530, 378, 140, 30);
		btn_4.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		contentPane.removeAll();
		        contentPane.revalidate();
		        contentPane.repaint();
        	}
        });
		panel.add(btn_4);
		
		btn_5 = new JButton("Editar docente");
		btn_5.setForeground(Color.WHITE);
		btn_5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btn_5.setFocusable(false);
		btn_5.setBackground(new Color(130, 36, 55));
		btn_5.setBounds(714, 378, 140, 30);
		panel.add(btn_5);
		
		btn_6 = new JButton("Consultar docente");
		btn_6.setForeground(Color.WHITE);
		btn_6.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btn_6.setFocusable(false);
		btn_6.setBackground(new Color(130, 36, 55));
		btn_6.setBounds(530, 543, 140, 30);
		panel.add(btn_6);
		
		btn_7 = new JButton("Eliminar docente");
		btn_7.setForeground(Color.WHITE);
		btn_7.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btn_7.setFocusable(false);
		btn_7.setBackground(new Color(130, 36, 55));
		btn_7.setBounds(714, 543, 140, 30);
		panel.add(btn_7);
		
		logoReg1 = new JLabel();
		logoReg1.setIcon(new ImageIcon(getClass().getResource("registrar.png")));
		logoReg1.setBounds(160,270,128,128);
		panel.add(logoReg1);
		
		logoReg2 = new JLabel();
		logoReg2.setIcon(new ImageIcon(getClass().getResource("registrarDocente.png")));
		logoReg2.setBounds(536,270,128,128);
		panel.add(logoReg2);
		
		logoEdit1 = new JLabel();
		logoEdit1.setIcon(new ImageIcon(getClass().getResource("editar.png")));
		logoEdit1.setBounds(340,270,128,128);
		panel.add(logoEdit1);
		
		logoEdit2 = new JLabel();
		logoEdit2.setIcon(new ImageIcon(getClass().getResource("editarDocente.png")));
		logoEdit2.setBounds(720,270,128,128);
		panel.add(logoEdit2);
		
		logoCon1 = new JLabel();
		logoCon1.setIcon(new ImageIcon(getClass().getResource("consultar.png")));
		logoCon1.setBounds(156,435,128,128);
		panel.add(logoCon1);
		
		logoElim1 = new JLabel();
		logoElim1.setIcon(new ImageIcon(getClass().getResource("eliminar.png")));
		logoElim1.setBounds(340,435,128,128);
		panel.add(logoElim1);
		
		
		logoCon2 = new JLabel();
		logoCon2.setIcon(new ImageIcon(getClass().getResource("consultarDocente.png")));
		logoCon2.setBounds(536,435,128,128);
		panel.add(logoCon2);
		
		logoElim2A = new JLabel();
		logoElim2A.setIcon(new ImageIcon(getClass().getResource("eliminarDocente.png")));
		logoElim2A.setBounds(720,435,128,128);
		panel.add(logoElim2A);
		
		
		
		labelGris = new JLabel("");
		labelGris.setBackground(new Color(208, 205, 193));
		labelGris.setOpaque(true);
		labelGris.setBounds(155, 270, 130, 126);
		panel.add(labelGris);
		
		
		labelGris = new JLabel("");
		labelGris.setBackground(new Color(208, 205, 193));
		labelGris.setOpaque(true);
		labelGris.setBounds(155, 270, 130, 126);
		panel.add(labelGris);
		

		labelGris_1 = new JLabel("");
		labelGris_1.setOpaque(true);
		labelGris_1.setBackground(new Color(208, 205, 193));
		labelGris_1.setBounds(339, 270, 130, 126);
		panel.add(labelGris_1);
		
		labelGris_2 = new JLabel("");
		labelGris_2.setOpaque(true);
		labelGris_2.setBackground(new Color(208, 205, 193));
		labelGris_2.setBounds(155, 435, 130, 126);
		panel.add(labelGris_2);
		
		labelGris_3 = new JLabel("");
		labelGris_3.setOpaque(true);
		labelGris_3.setBackground(new Color(208, 205, 193));
		labelGris_3.setBounds(339, 435, 130, 126);
		panel.add(labelGris_3);
		
		labelGris_4 = new JLabel("");
		labelGris_4.setOpaque(true);
		labelGris_4.setBackground(new Color(208, 205, 193));
		labelGris_4.setBounds(535, 435, 130, 126);
		panel.add(labelGris_4);
		
		labelGris_5 = new JLabel("");
		labelGris_5.setOpaque(true);
		labelGris_5.setBackground(new Color(208, 205, 193));
		labelGris_5.setBounds(719, 435, 130, 126);
		panel.add(labelGris_5);
		
		labelGris_6 = new JLabel("");
		labelGris_6.setOpaque(true);
		labelGris_6.setBackground(new Color(208, 205, 193));
		labelGris_6.setBounds(535, 270, 130, 126);
		panel.add(labelGris_6);
		
		labelGris_7 = new JLabel("");
		labelGris_7.setOpaque(true);
		labelGris_7.setBackground(new Color(208, 205, 193));
		labelGris_7.setBounds(719, 270, 130, 126);
		panel.add(labelGris_7);		
		
	}
	
	public void elementos()
	{
		
		JLabel labelEscuela = new JLabel("Colegio Hogwarts de Magia y Hechiceria");
		labelEscuela.setBounds(166, 42, 753, 80);
		labelEscuela.setForeground(Color.white);
		labelEscuela.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 37));
		panel.add(labelEscuela);
		
		logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("escudo.png")));
		logo.setBounds(51,40,100,100);
		panel.add(logo);
		
		JLabel labelEslogan = new JLabel("“Draco Dormiens Nunquam Titilandus”",0);
		labelEslogan.setForeground(new Color(255, 255, 255));
		labelEslogan.setFont(new Font("Tahoma", Font.PLAIN | Font.ITALIC, 14));
		labelEslogan.setBounds(0, 585, 996, 20);
		panel.add(labelEslogan);
		
		panelSur = new JPanel();
		panelSur.setBackground(new Color(199, 146, 66));
		contentPane.add(panelSur, BorderLayout.SOUTH);
	}
	
	public void elementosV2()	{
		
		JLabel labelEscuela = new JLabel("Colegio Hogwarts de Magia y Hechiceria");
		labelEscuela.setBounds(108, 35, 753, 80);
		labelEscuela.setForeground(Color.white);
		labelEscuela.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 37));
		panel.add(labelEscuela);
		
		JLabel labelGrisV2 = new JLabel("");
		labelGrisV2.setBackground(new Color(208, 205, 193));
		labelGrisV2.setOpaque(true);
		labelGrisV2.setBounds(58, 150, 750, 550);
		panel.add(labelGrisV2);
		
		logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("escudo.png")));
		logo.setBounds(10,33,100,100);
		panel.add(logo);
		
		JLabel labelEslogan = new JLabel("“Draco Dormiens Nunquam Titilandus”",0);
		labelEslogan.setForeground(new Color(255, 255, 255));
		labelEslogan.setFont(new Font("Tahoma", Font.PLAIN | Font.ITALIC, 14));
		labelEslogan.setBounds(0, 740, 867, 20);
		panel.add(labelEslogan);
		
		
		panelSur = new JPanel();
		panelSur.setBackground(new Color(199, 146, 66));
		contentPane.add(panelSur, BorderLayout.SOUTH);
	}
	
	public void registrarAlumno() {

		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelMenu, BorderLayout.NORTH);	
		menuVertical(); // y menu Inicio - Cerrar Sesion
		
		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
	    panel.setPreferredSize(new Dimension(862, 800)); 
	    
	    JLabel labelRegistro = new JLabel("Registrar alumno");
		labelRegistro.setVerticalAlignment(SwingConstants.TOP);
		labelRegistro.setForeground(new Color(0, 0, 0));
		labelRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegistro.setBounds(102, 170, 687, 30);
		panel.add(labelRegistro);
	    
	    logoPerfil = new JLabel();
	    logoPerfil.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
	    logoPerfil.setBounds(648,220,110,130);
	    panel.add(logoPerfil);
	    
	    JLabel labelMatricula = new JLabel("");
	    labelMatricula.setBackground(new Color(255, 255, 255));
	    labelMatricula.setOpaque(true);
	    labelMatricula.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    labelMatricula.setBounds(350, 600, 228, 20);
	    panel.add(labelMatricula);
	    
	    JLabel lblMatrcula = new JLabel("Matrícula: ", SwingConstants.LEFT);
	    lblMatrcula.setForeground(new Color(128, 0, 0));
	    lblMatrcula.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblMatrcula.setBounds(102, 600, 180, 20);
	    panel.add(lblMatrcula);
	    
	    JButton btnFoto = new JButton("Desde archivo");
	    btnFoto.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    btnFoto.setBounds(648, 363, 110, 21);
	    btnFoto.setForeground(new Color(255, 255, 255));
	    btnFoto.setBackground(new Color(130, 36, 55));
	    btnFoto.setFocusable(false);
	    panel.add(btnFoto);
	    
	    JLabel lblCasa = new JLabel("Casa: *", SwingConstants.LEFT);
	    lblCasa.setForeground(new Color(128, 0, 0));
	    lblCasa.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblCasa.setBounds(350, 552, 180, 20);
	    panel.add(lblCasa);
	    
	    textCasa = new JTextField();
	    textCasa.setColumns(10);
	    textCasa.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textCasa.setBounds(419, 552, 159, 20);
	    panel.add(textCasa);
	    
	    textAño = new JTextField();
	    textAño.setColumns(10);
	    textAño.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textAño.setBounds(171, 552, 159, 20);
	    textAño.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isDigit(l)) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
	    panel.add(textAño);
	    
	    JLabel lblAo = new JLabel("Año: *", SwingConstants.LEFT);
	    lblAo.setForeground(new Color(128, 0, 0));
	    lblAo.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblAo.setBounds(102, 552, 180, 20);
	    panel.add(lblAo);
	    
	    JLabel lblTelfono = new JLabel("Teléfono: *", SwingConstants.LEFT);
		lblTelfono.setForeground(new Color(128, 0, 0));
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelfono.setBounds(102, 501, 180, 20);
		panel.add(lblTelfono);
		
		textTel = new JTextField();
		textTel.setColumns(10);
		textTel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		textTel.setBounds(350, 501, 228, 20);
		textTel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isDigit(l)) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
		panel.add(textTel);
	    
	    textPais = new JTextField();
	    textPais.setColumns(10);
	    textPais.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textPais.setBounds(350, 392, 228, 20);
	    panel.add(textPais);
	    
	    JLabel lblPais = new JLabel("País de nacimiento: *", SwingConstants.LEFT);
	    lblPais.setForeground(new Color(128, 0, 0));
	    lblPais.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblPais.setBounds(102, 392, 180, 20);
	    panel.add(lblPais);
	    
	    textEmail = new JTextField();
	    textEmail.setColumns(10);
	    textEmail.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textEmail.setBounds(350, 447, 228, 20);
	    panel.add(textEmail);
	    
	    JLabel lblCorreoElectr = new JLabel("Correo electrónico: *", SwingConstants.LEFT);
	    lblCorreoElectr.setForeground(new Color(128, 0, 0));
	    lblCorreoElectr.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblCorreoElectr.setBounds(102, 447, 180, 20);
	    panel.add(lblCorreoElectr);
	    
	    JLabel lblFoto = new JLabel("");
	    lblFoto.setOpaque(true);
	    lblFoto.setBackground(new Color(240, 240, 240));
	    lblFoto.setBounds(648, 223, 110, 130);
	    panel.add(lblFoto);
	    
	    JComboBox comboGenero = new JComboBox();
	    comboGenero.setModel(new DefaultComboBoxModel(new String[] {"  ", "Hombre", "Mujer\t"}));
	    comboGenero.setBounds(350, 344, 228, 21);
	    panel.add(comboGenero);
	    
	    JLabel lblGenero = new JLabel("Genero: *", SwingConstants.LEFT);
	    lblGenero.setForeground(new Color(128, 0, 0));
	    lblGenero.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblGenero.setBounds(102, 344, 180, 20);
	    panel.add(lblGenero);
	    
	    
	    JLabel labelID = new JLabel("Nombres: *",SwingConstants.LEFT);
	    labelID.setForeground(new Color(128, 0, 0));
		labelID.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelID.setBounds(102,210, 100, 20);
		panel.add(labelID);
		
		JLabel labelPS = new JLabel("Apellidos: *", SwingConstants.LEFT);
		labelPS.setForeground(new Color(128, 0, 0));
		labelPS.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelPS.setBounds(350, 210, 100, 20);
		panel.add(labelPS);
	    
	    textNombres = new JTextField();
	    textNombres.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		textNombres.setBounds(102, 244, 228, 20);
		panel.add(textNombres);
		textNombres.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isLetter(l) && l !=32) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
		textNombres.setColumns(10);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		textApellidos.setBounds(350, 244, 228, 20);
		textApellidos.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isLetter(l) && l !=32) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
		panel.add(textApellidos);
	  
		textFecha = new JTextField("DD/MM/AAAA");
	    textFecha.setColumns(10);
	    textFecha.setForeground(Color.GRAY);
	    textFecha.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textFecha.setBounds(350, 289, 228, 20);
	    textFecha.addFocusListener(new FocusListener() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (textFecha.getText().equals("DD/MM/AAAA")) { 
	                textFecha.setText(""); 
	                textFecha.setForeground(Color.BLACK); 
	            }
	        }
	        @Override
	        public void focusLost(FocusEvent e) {
	            if (textFecha.getText().isEmpty()) { 
	                textFecha.setText("DD/MM/AAAA"); 
	                textFecha.setForeground(Color.GRAY);
	            }
	        }
	    });
	    textFecha.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isDigit(l) && l !='/') {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
	    panel.add(textFecha);
	    
	    JButton btnGuardar = new JButton("Guardar");
	    btnGuardar.setBounds(391, 650, 85, 21);
	    btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    btnGuardar.setForeground(new Color(255, 255, 255));
	    btnGuardar.setBackground(new Color(130, 36, 55));
	    btnGuardar.setFocusable(false);
	    btnGuardar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (!textNombres.getText().isEmpty() &&
	                    !textApellidos.getText().isEmpty() &&
	                    !textFecha.getText().equals("DD/MM/AAAA") &&
	                    !textTel.getText().isEmpty() &&
	                    !textPais.getText().isEmpty() &&
	                    !textEmail.getText().isEmpty() &&
	                    !textAño.getText().isEmpty() &&
	                    !textCasa.getText().isEmpty() &&
	                    !comboGenero.getSelectedItem().toString().trim().isEmpty()) { 
	                System.out.println("Datos guardados exitosamente.");
	                Random rand = new Random();
	                int matricula = rand.nextInt(1000000); // Puedes ajustar el rango según sea necesario
	                labelMatricula.setText(String.valueOf(matricula));
	                btnRegistrarNuevo.setVisible(true);
	                panel.revalidate();
                    panel.repaint();
	                btnGuardar.setEnabled(false); 
	            } else {
	                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
	                if (textNombres.getText().isEmpty()) {
	                    textNombres.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else textNombres.setBorder(null);
	                if (textApellidos.getText().isEmpty()) {
	                    textApellidos.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else textNombres.setBorder(null);
	                if (textFecha.getText().equals("DD/MM/AAAA")) {
	                    textFecha.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else textNombres.setBorder(null);
	                if (textTel.getText().isEmpty()) {
	                    textTel.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else textNombres.setBorder(null);
	                if (textPais.getText().isEmpty()) {
	                    textPais.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else textNombres.setBorder(null);
	                if (textEmail.getText().isEmpty()) {
	                    textEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else textNombres.setBorder(null);
	                if (textAño.getText().isEmpty()) {
	                    textAño.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else textNombres.setBorder(null);
	                if (textCasa.getText().isEmpty()) {
	                    textCasa.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else textNombres.setBorder(null);
	                if (comboGenero.getSelectedItem().toString().trim().isEmpty()) {
	                    ((JComponent) comboGenero.getRenderer()).setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else textNombres.setBorder(null);
	            }
	            
	        }
	    });
	    panel.add(btnGuardar);
	    
	    JLabel labelFecha = new JLabel("Fecha de nacimiento: *", SwingConstants.LEFT);
	    labelFecha.setForeground(new Color(128, 0, 0));
	    labelFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
	    labelFecha.setBounds(102, 289, 196, 20);
	    panel.add(labelFecha);
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	//	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		btnRegistrarNuevo = new JButton("Registrar Nuevo");
        btnRegistrarNuevo.setBounds(633, 650, 130, 21);
        btnRegistrarNuevo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRegistrarNuevo.setForeground(new Color(255, 255, 255));
        btnRegistrarNuevo.setBackground(new Color(130, 36, 55));
        btnRegistrarNuevo.setFocusable(false);
        btnRegistrarNuevo.setVisible(false);
        btnRegistrarNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textNombres.setText("");  textApellidos.setText("");
                textFecha.setText("DD/MM/AAAA"); textFecha.setForeground(Color.GRAY);
                textTel.setText(""); textPais.setText(""); textEmail.setText("");
                textAño.setText(""); textCasa.setText("");  comboGenero.setSelectedIndex(0); labelMatricula.setText("");
                btnGuardar.setEnabled(true); 
                panel.remove(btnRegistrarNuevo);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnRegistrarNuevo);
		
		elementosV2();
		
		JLabel labelGrisV2 = new JLabel("");
		labelGrisV2.setBackground(new Color(208, 205, 193));
		labelGrisV2.setOpaque(true);
		labelGrisV2.setBounds(58, 150, 750, 550);
		panel.add(labelGrisV2);
	}

	
	
	public void consultarAlumno() {
	//	menu();
		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
	    panel.setPreferredSize(new Dimension(862, 800)); 
	    menuVertical();
	    
	    JLabel logoHP = new JLabel();
	    logoHP.setIcon(new ImageIcon(getClass().getResource("Harry.jpg")));
	    logoHP.setVisible(false);
	    logoHP.setBounds(648,291,110,130);
	    panel.add(logoHP);
	    
	    JLabel lblFecha = new JLabel("Fecha de nacimiento: ", SwingConstants.LEFT);
	    lblFecha.setForeground(new Color(128, 0, 0));
	    lblFecha.setVisible(false);
	    lblFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblFecha.setBounds(102, 382, 180, 20);
	    panel.add(lblFecha);
	    
	    JLabel logoCasa = new JLabel();
	    logoCasa.setVisible(false);
	    logoCasa.setIcon(new ImageIcon(getClass().getResource("Gryffindor.png")));
	    logoCasa.setBounds(643,535,115,130);
	    panel.add(logoCasa);
	    
	    JLabel  labelCons= new JLabel("Consultar alumno");
	    labelCons.setVerticalAlignment(SwingConstants.TOP);
	    labelCons.setForeground(new Color(0, 0, 0));
	    labelCons.setFont(new Font("Tahoma", Font.BOLD, 20));
	    labelCons.setBounds(102, 170, 687, 30);
		panel.add(labelCons);
		
		JLabel labelID = new JLabel("ID del alumno: ", SwingConstants.LEFT);
		labelID.setForeground(new Color(128, 0, 0));
		labelID.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelID.setBounds(102, 212, 150, 13);
		panel.add(labelID);
		
		JLabel lblCasa = new JLabel("Casa: ", SwingConstants.LEFT);
	    lblCasa.setForeground(new Color(128, 0, 0));
	    lblCasa.setVisible(false);
	    lblCasa.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblCasa.setBounds(350, 639, 180, 20);
	    panel.add(lblCasa);
	    
	    JLabel lblAo = new JLabel("Año: ", SwingConstants.LEFT);
	    lblAo.setForeground(new Color(128, 0, 0));
	    lblAo.setVisible(false);
	    lblAo.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblAo.setBounds(102, 639, 180, 20);
	    panel.add(lblAo);
	    
	    JLabel lblTelfono = new JLabel("Teléfono: ", SwingConstants.LEFT);
		lblTelfono.setForeground(new Color(128, 0, 0));
		lblTelfono.setVisible(false);
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelfono.setBounds(102, 588, 180, 20);
		panel.add(lblTelfono);
	    
	    JLabel lblPais = new JLabel("País de nacimiento: ", SwingConstants.LEFT);
	    lblPais.setForeground(new Color(128, 0, 0));
	    lblPais.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblPais.setVisible(false);
	    lblPais.setBounds(102, 479, 180, 20);
	    panel.add(lblPais);
	    
	    
	    JLabel lblCorreoElectr = new JLabel("Correo electrónico: ", SwingConstants.LEFT);
	    lblCorreoElectr.setForeground(new Color(128, 0, 0));
	    lblCorreoElectr.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblCorreoElectr.setBounds(102, 534, 180, 20);
	    lblCorreoElectr.setVisible(false);
	    panel.add(lblCorreoElectr);
	    
	    JLabel lblGenero = new JLabel("Genero: ", SwingConstants.LEFT);
	    lblGenero.setForeground(new Color(128, 0, 0));
	    lblGenero.setVisible(false);
	    lblGenero.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblGenero.setBounds(102, 431, 180, 20);
	    panel.add(lblGenero);

	    JLabel labelIDA = new JLabel("Nombres: ",SwingConstants.LEFT);
	    labelIDA.setForeground(new Color(128, 0, 0));
	    labelIDA.setVisible(false);
		labelIDA.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelIDA.setBounds(102,291, 100, 20);
		panel.add(labelIDA);
		
		JLabel labelPS = new JLabel("Apellidos: ", SwingConstants.LEFT);
		labelPS.setForeground(new Color(128, 0, 0));
		labelPS.setVisible(false);
		labelPS.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelPS.setBounds(102, 331, 100, 20);
		panel.add(labelPS);
		
		casa = new JTextField("Gryffindor");
	    casa.setBounds(419, 639, 159, 20);
	    casa.setOpaque(false); casa.setBorder(null); casa.setEditable(false); casa.setFocusable(false);
	    casa.setVisible(false); 
	    casa.setFont(new Font("Tahoma", Font.BOLD, 16));
	    panel.add(casa);
	    
	    anio = new JTextField("6");
	    anio.setBounds(171, 639, 159, 20);
	    anio.setOpaque(false); anio.setBorder(null); anio.setEditable(false); anio.setFocusable(false);
	    anio.setVisible(false);
	    anio.setFont(new Font("Tahoma", Font.BOLD, 16));
	    panel.add(anio);
		
	    tel= new JTextField ("777056123");
		tel.setVisible(false);
		tel.setOpaque(false); tel.setBorder(null); tel.setEditable(false); tel.setFocusable(false);
		tel.setFont(new Font("Tahoma", Font.BOLD, 16));
		tel.setBounds(350, 588, 228, 20);
		panel.add(tel);
		
		
		comboGenero = new JTextField("Hombre");
		comboGenero.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboGenero.setBounds(350, 431, 228, 21);
		comboGenero.setVisible(false); 
		comboGenero.setEditable(false);
		comboGenero.setOpaque(false); // Establecer como no opaco para que no parezca un campo de texto
		comboGenero.setBorder(null); // Eliminar el borde
		panel.add(comboGenero);
		
		pais = new JTextField("Inglaterra");
	    pais.setFont(new Font("Tahoma", Font.BOLD, 16));
	    pais.setBounds(350, 479, 228, 20);
	    pais.setOpaque(false); pais.setBorder(null); pais.setEditable(false); pais.setFocusable(false);
	    pais.setVisible(false);
	    panel.add(pais);
	    
	    email = new JTextField("hp31@wizardingworld.com");
	    email.setFont(new Font("Tahoma", Font.BOLD, 16));
	    email.setBounds(350, 534, 228, 20);
	    email.setOpaque(false); email.setBorder(null); email.setEditable(false); email.setFocusable(false);
	    email.setVisible(false);
	    panel.add(email);
	        
	       
		nombre = new JTextField("Harry James");
	    nombre.setFont(new Font("Tahoma", Font.BOLD, 16));
	    nombre.setVisible(false);
	    nombre.setOpaque(false); nombre.setBorder(null); nombre.setEditable(false); nombre.setFocusable(false);
		nombre.setBounds(350, 291, 228, 20);
		panel.add(nombre);
		
		apellido = new JTextField("Potter");
		apellido.setVisible(false);
		apellido.setOpaque(false); apellido.setBorder(null); apellido.setEditable(false); apellido.setFocusable(false);
		apellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		apellido.setBounds(350, 331, 228, 20);
		panel.add(apellido);
	  
		fecha = new JTextField("31/07/1980");
		fecha.setVisible(false);
		fecha.setOpaque(false); fecha.setBorder(null); fecha.setEditable(false); fecha.setFocusable(false);
		fecha.setFont(new Font("Tahoma", Font.BOLD, 16));
	    fecha.setBounds(350, 376, 228, 20);
	    panel.add(fecha);
		
		textID = new JTextField();
		textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		textID.setBounds(102, 240, 228, 20);
		panel.add(textID);
		textID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isDigit(l)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
		textID.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(400, 240, 130, 21);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(130, 36, 55));
		btnBuscar.setFocusable(false);
		btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String id = textID.getText();
            	if (id.length()<=0)
				{
					textID.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					 textID.setBorder(null);
				}
            	if(id.equals("12345"))
				{
            		textID.setEnabled(false);
            		btnBuscar.setVisible(false);
            		btnConsNuevo.setVisible(true);
            		btnDescargar.setVisible(true);
            		logoHP.setVisible(true); logoCasa.setVisible(true); lblCasa.setVisible(true); lblAo.setVisible(true); lblTelfono.setVisible(true);lblPais.setVisible(true); lblFecha.setVisible(true);
            		lblCorreoElectr.setVisible(true);  comboGenero.setVisible(true); lblGenero.setVisible(true); labelIDA.setVisible(true); labelPS.setVisible(true);
            		nombre.setVisible(true);apellido.setVisible(true);email.setVisible(true);tel.setVisible(true);fecha.setVisible(true);pais.setVisible(true);casa.setVisible(true); anio.setVisible(true);
            		comboGenero.setVisible(true); 
            		if (actualizado) {
            			nombre.setText(nuevoNombre);
                        apellido.setText(nuevoApellido);
                        email.setText(nuevoEmail);
                        tel.setText(nuevoTel); 
                        fecha.setText(nuevaFecha);
                        pais.setText(nuevoPais);
                        casa.setText(nuevaCasa);
                        comboGenero.setText(nuevoGenero);
            		} else {   
            		nombre.setText("Harry James");
                    apellido.setText("Potter");
                    email.setText("hp31@wizardingworld.com");
                    tel.setText("777056123");
                    fecha.setText("31/07/1980");
                    pais.setText("Inglaterra");
                    casa.setText("Gryffindor");
            		}
            	}else {
					JOptionPane.showMessageDialog(null, "ID de alumno no encontrado","Error", JOptionPane.WARNING_MESSAGE);
				}
            	
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnBuscar);
        
        btnConsNuevo = new JButton("Consultar Nuevo");
        btnConsNuevo.setBounds(600, 240, 130, 21);
        btnConsNuevo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnConsNuevo.setForeground(new Color(255, 255, 255));
        btnConsNuevo.setBackground(new Color(130, 36, 55));
        btnConsNuevo.setFocusable(false);
        btnConsNuevo.setVisible(false);
        btnConsNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textID.setText(""); 
                textID.setEnabled(true);
                btnConsNuevo.setVisible(false);
                btnDescargar.setVisible(false);
                btnBuscar.setVisible(true);
                logoHP.setVisible(false); logoCasa.setVisible(false); lblCasa.setVisible(false); lblAo.setVisible(false); lblTelfono.setVisible(false);lblPais.setVisible(false); lblFecha.setVisible(false);
        		lblCorreoElectr.setVisible(false);  comboGenero.setVisible(false); lblGenero.setVisible(false); labelIDA.setVisible(false); labelPS.setVisible(false);
        		nombre.setVisible(false);apellido.setVisible(false);email.setVisible(false);tel.setVisible(false);fecha.setVisible(false);pais.setVisible(false);casa.setVisible(false);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnConsNuevo);
        
        btnDescargar = new JButton("Descargar");
        btnDescargar.setBounds(400, 240, 130, 21);
        btnDescargar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDescargar.setForeground(new Color(255, 255, 255));
        btnDescargar.setBackground(new Color(130, 36, 55));
        btnDescargar.setFocusable(false);
        btnDescargar.setVisible(false);
        btnDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textID.setText(""); 
                btnDescargar.setVisible(true);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnDescargar);
        
        
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	//	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		elementosV2();
	}
	
	public void recuperar() { //Recuperar contraseña
		menuI_R_A();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelMenu, BorderLayout.NORTH);	

		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
		contentPane.add(panel, BorderLayout.CENTER);
		imagenes();
		
		JLabel lblInstrucciones = new JLabel("Recuperar contraseña");
		lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
	    lblInstrucciones.setForeground(Color.white);
	    lblInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 20));
	    lblInstrucciones.setBounds(309, 153, 380, 30);
	    panel.add(lblInstrucciones);
		
		JLabel lblContra = new JLabel("123");
		lblContra.setForeground(new Color(130, 36, 55));
		lblContra.setHorizontalAlignment(SwingConstants.CENTER);
		lblContra.setVisible(false);
		lblContra.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblContra.setBounds(349, 376, 300, 20);
		panel.add(lblContra);
		
		JLabel lblContraseña = new JLabel("Su contraseña es: ");
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblContraseña.setVisible(false);
		lblContraseña.setBounds(349, 346, 200, 20);
		panel.add(lblContraseña);
		
		JLabel labelID = new JLabel("ID DE USUARIO",0);
		labelID.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelID.setBounds(309,207, 380, 39);
		panel.add(labelID);
		
		nombres = new JTextField();
		nombres.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		nombres.setBounds(349, 265, 300, 40);
		panel.add(nombres);
		nombres.setColumns(10);
		
		JButton btnBus = new JButton("Buscar");
		btnBus.setBorderPainted(false);
		btnBus.setForeground(new Color(255, 255, 255));
		btnBus.setBackground(new Color(130, 36, 55));
		btnBus.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		btnBus.setBorder(new LineBorder(new Color(0, 189, 189), 5));
		btnBus.setBounds(439, 450, 120, 30);
		btnBus.setFocusable(false);
		btnBus.addActionListener(new ActionListener()
		{
        	public void actionPerformed(ActionEvent e) {
        		String usr = nombres.getText();
				if (usr.length()<=0){
					nombres.setBorder(BorderFactory.createLineBorder(Color.red,3));
				} else {
					 nombres.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
				}
				if(usr.equals("Yo")) {
				        contentPane.revalidate();
				        contentPane.repaint();
				        lblContra.setVisible(true);
				        lblContraseña.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado","Error", JOptionPane.WARNING_MESSAGE);
				}               
			}});      
		panel.add(btnBus);	
		
		JLabel labelGris = new JLabel("");
		labelGris.setOpaque(true);
		labelGris.setBackground(new Color(208, 205, 193));
		labelGris.setBounds(309, 188,380,330);
		panel.add(labelGris);
		
		elementos();
	}
	public void comoAcceder() {
		menuI_R_A();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelMenu, BorderLayout.NORTH);

		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
		contentPane.add(panel, BorderLayout.CENTER);
		imagenes();
		
		JLabel lblInstrucciones = new JLabel("Para acceder al sistema, sigue estos pasos:");
	    lblInstrucciones.setForeground(Color.white);
	    lblInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 20));
	    lblInstrucciones.setBounds(272, 190, 460, 30);
	    panel.add(lblInstrucciones);

	    JTextArea txtPasos = new JTextArea();
	    txtPasos.setWrapStyleWord(true);
	    txtPasos.setFocusable(false);
	    txtPasos.setForeground(Color.black);
	    txtPasos.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    txtPasos.setLineWrap(true);
	    txtPasos.setBounds(278, 295, 440, 180);
	    txtPasos.setText("1. Abre el programa de control escolar.\n" +
	                     "2. En la página de inicio de sesión, introduce tu ID de usuario y contraseña.\n" +
	                     "3. Haz clic en el botón 'Acceder'.\n" +
	                     "4. Si los datos son correctos, serás redirigido al menú principal del sistema.");
	    txtPasos.setOpaque(false);
	    txtPasos.setEditable(false);
	    panel.add(txtPasos);
	    
	    JLabel labelGris = new JLabel("");
		labelGris.setOpaque(true);
		labelGris.setBackground(new Color(208, 205, 193));
		labelGris.setBounds(272, 250,452,235);
		panel.add(labelGris);
		
		elementos();
	}
	
	public void eliminarAlumno() {
		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
	    panel.setPreferredSize(new Dimension(862, 800)); 
	    menuVertical();
	    
	    JLabel cedric = new JLabel();
        cedric.setIcon(new ImageIcon(getClass().getResource("cedric.jpg")));
        cedric.setBounds(378,380,110,130);
        cedric.setVisible(false);
	    panel.add(cedric);
		
	    JLabel labelRegistro = new JLabel("Eliminar alumno");
		labelRegistro.setVerticalAlignment(SwingConstants.TOP);
		labelRegistro.setForeground(new Color(0, 0, 0));
		labelRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegistro.setBounds(102, 170, 687, 30);
		panel.add(labelRegistro);
		
		
		JLabel labelID = new JLabel("ID del alumno: ", SwingConstants.LEFT);
		labelID.setForeground(new Color(128, 0, 0));
		labelID.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelID.setBounds(102, 212, 150, 13);
		panel.add(labelID);
		
		textID = new JTextField();
		textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		textID.setBounds(102, 240, 228, 20);
		panel.add(textID);
		textID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isDigit(l)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
		textID.setColumns(10);
		
		JLabel lbConfirmarcion = new JLabel();
		lbConfirmarcion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbConfirmarcion.setHorizontalAlignment(SwingConstants.CENTER);
		lbConfirmarcion.setBounds(215, 336, 450, 20);
		lbConfirmarcion.setVisible(false);
		panel.add(lbConfirmarcion);
		
		btnBuscar = new JButton("Eliminar");
		btnBuscar.setActionCommand("Eliminar");
		btnBuscar.setBounds(400, 240, 130, 21);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(130, 36, 55));
		btnBuscar.setFocusable(false);
		btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String id = textID.getText();
            	if (id.length()<=0)
				{
					textID.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					 textID.setBorder(null);
					 int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar a este alumno?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		                if (opcion == JOptionPane.YES_OPTION) {
		                    if(id.equals("54321")) {
		                        textID.setEnabled(false);
		                        btnBuscar.setVisible(false);
		                        lbConfirmarcion.setText("Alumno con ID '" + id + "' eliminado con éxito");                   
		                        lbConfirmarcion.setVisible(true);
		                        cedric.setVisible(true);
		                        btnConsNuevo.setVisible(true);
		                    } else {
		                        JOptionPane.showMessageDialog(null, "ID de alumno no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
		                    }
		                }
				}
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnBuscar);
        
        btnConsNuevo = new JButton("Eliminar Nuevo");
        btnConsNuevo.setActionCommand("Eliminar Nuevo");
        btnConsNuevo.setBounds(600, 240, 130, 21);
        btnConsNuevo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnConsNuevo.setForeground(new Color(255, 255, 255));
        btnConsNuevo.setBackground(new Color(130, 36, 55));
        btnConsNuevo.setFocusable(false);
        btnConsNuevo.setVisible(false);
        btnConsNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textID.setText(""); 
                textID.setEnabled(true);
                panel.remove(btnConsNuevo);
                btnBuscar.setVisible(true);
                lbConfirmarcion.setVisible(false);
                cedric.setVisible(false);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnConsNuevo);
        
        
	    
        JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
        elementosV2();
	}
	
	public void editarAlumno() {
		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
	    panel.setPreferredSize(new Dimension(862, 800)); 
	    menuVertical();
	    
	    JLabel logoHP = new JLabel();
	    logoHP.setIcon(new ImageIcon(getClass().getResource("Harry.jpg")));
	    logoHP.setVisible(false);
	    
	    JLabel lblFecha = new JLabel("Fecha de nacimiento: ", SwingConstants.LEFT);
	    lblFecha.setForeground(new Color(128, 0, 0));
	    lblFecha.setVisible(false);
	    lblFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblFecha.setBounds(102, 382, 180, 20);
	    panel.add(lblFecha);

	    JLabel logoCasa = new JLabel();
	    logoCasa.setVisible(false);
	    logoCasa.setIcon(new ImageIcon(getClass().getResource("Gryffindor.png")));
	    logoCasa.setBounds(643,535,115,130);
	    panel.add(logoCasa);
	    
	    JLabel  labelEdit= new JLabel("Editar alumno");
	    labelEdit.setVerticalAlignment(SwingConstants.TOP);
	    labelEdit.setForeground(new Color(0, 0, 0));
	    labelEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
	    labelEdit.setBounds(102, 170, 687, 30);
		panel.add(labelEdit);
		
		JLabel labelID = new JLabel("ID del alumno: ", SwingConstants.LEFT);
		labelID.setForeground(new Color(128, 0, 0));
		labelID.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelID.setBounds(102, 212, 150, 13);
		panel.add(labelID);
		
		JLabel lblCasa = new JLabel("Casa: ", SwingConstants.LEFT);
	    lblCasa.setForeground(new Color(128, 0, 0));
	    lblCasa.setVisible(false);
	    lblCasa.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblCasa.setBounds(350, 639, 180, 20);
	    panel.add(lblCasa);
	    
	    JLabel lblAo = new JLabel("Año: ", SwingConstants.LEFT);
	    lblAo.setForeground(new Color(128, 0, 0));
	    lblAo.setVisible(false);
	    lblAo.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblAo.setBounds(102, 639, 180, 20);
	    panel.add(lblAo);
	    
	    JLabel lblTelfono = new JLabel("Teléfono: ", SwingConstants.LEFT);
		lblTelfono.setForeground(new Color(128, 0, 0));
		lblTelfono.setVisible(false);
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelfono.setBounds(102, 588, 180, 20);
		panel.add(lblTelfono);
		
		JLabel lblPais = new JLabel("País de nacimiento: ", SwingConstants.LEFT);
	    lblPais.setForeground(new Color(128, 0, 0));
	    lblPais.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblPais.setVisible(false);
	    lblPais.setBounds(102, 479, 180, 20);
	    panel.add(lblPais);
	    
	    
	    JLabel lblCorreoElectr = new JLabel("Correo electrónico: ", SwingConstants.LEFT);
	    lblCorreoElectr.setForeground(new Color(128, 0, 0));
	    lblCorreoElectr.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblCorreoElectr.setBounds(102, 534, 180, 20);
	    lblCorreoElectr.setVisible(false);
	    panel.add(lblCorreoElectr);
	    
	    JLabel lblGenero = new JLabel("Genero: ", SwingConstants.LEFT);
	    lblGenero.setForeground(new Color(128, 0, 0));
	    lblGenero.setVisible(false);
	    lblGenero.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblGenero.setBounds(102, 431, 180, 20);
	    panel.add(lblGenero);
	    
	    
	    JLabel labelIDA = new JLabel("Nombres: ",SwingConstants.LEFT);
	    labelIDA.setForeground(new Color(128, 0, 0));
	    labelIDA.setVisible(false);
		labelIDA.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelIDA.setBounds(102,291, 100, 20);
		panel.add(labelIDA);
		
		JLabel labelPS = new JLabel("Apellidos: ", SwingConstants.LEFT);
		labelPS.setForeground(new Color(128, 0, 0));
		labelPS.setVisible(false);
		labelPS.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelPS.setBounds(102, 331, 100, 20);
		panel.add(labelPS);
		
		casa = new JTextField("Gryffindor");
	    casa.setBounds(419, 639, 159, 20);
	    casa.setVisible(false); 
	    casa.setFont(new Font("Tahoma", Font.BOLD, 16));
	    panel.add(casa);
	    
	    anio = new JTextField("6");
	    anio.setBounds(171, 639, 159, 20);
	    anio.setVisible(false);
	    anio.setFont(new Font("Tahoma", Font.BOLD, 16));
	    panel.add(anio);
		
	    tel= new JTextField ("777056123");
		tel.setVisible(false);
		tel.setFont(new Font("Tahoma", Font.BOLD, 16));
		tel.setBounds(350, 588, 228, 20);
		panel.add(tel);
	    
		pais = new JTextField("Inglaterra");
	    pais.setFont(new Font("Tahoma", Font.BOLD, 16));
	    pais.setBounds(350, 479, 228, 20);
	    pais.setVisible(false);
	    panel.add(pais);
	    
	    email = new JTextField("hp31@wizardingworld.com");
	    email.setFont(new Font("Tahoma", Font.BOLD, 16));
	    email.setBounds(350, 534, 228, 20);
	    email.setVisible(false);
	    panel.add(email);
	        
	       
	    JComboBox comboGenero = new JComboBox();
	    comboGenero.setModel(new DefaultComboBoxModel(new String[] {"  ", "Hombre", "Mujer"})); // Eliminé el "\t" extra
	    comboGenero.setBounds(350, 431, 228, 21);
	    comboGenero.setFont(new Font("Tahoma", Font.BOLD, 16));
	    comboGenero.setSelectedIndex(1); 
	    comboGenero.setVisible(false);
	    panel.add(comboGenero);
	   
	    
		nombre = new JTextField("Harry James");
	    nombre.setFont(new Font("Tahoma", Font.BOLD, 16));
	    nombre.setVisible(false);
		nombre.setBounds(350, 291, 228, 20);
		panel.add(nombre);
		
		apellido = new JTextField("Potter");
		apellido.setVisible(false);
		apellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		apellido.setBounds(350, 331, 228, 20);
		panel.add(apellido);
	  
		fecha = new JTextField("31/07/1980");
		fecha.setVisible(false);
		fecha.setFont(new Font("Tahoma", Font.BOLD, 16));
	    fecha.setBounds(350, 376, 228, 20);
	    panel.add(fecha);
		
		textID = new JTextField();
		textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		textID.setBounds(102, 240, 228, 20);
		panel.add(textID);
		textID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isDigit(l)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
		textID.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(400, 240, 130, 21);
        btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnActualizar.setForeground(new Color(255, 255, 255));
        btnActualizar.setBackground(new Color(130, 36, 55));
        btnActualizar.setFocusable(false);
        btnActualizar.setVisible(false);
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnActualizar.setVisible(false);
                nombre.setFocusable(false);apellido.setFocusable(false);email.setFocusable(false);tel.setFocusable(false);fecha.setFocusable(false);pais.setFocusable(false);casa.setFocusable(false);
                nombre.setEditable(false);apellido.setEditable(false);email.setEditable(false);tel.setEditable(false);fecha.setEditable(false);pais.setEditable(false);casa.setEditable(false); 
                actualizado=true; 
                nuevoNombre = nombre.getText();
                nuevoApellido = apellido.getText();
                nuevoEmail = email.getText();
                nuevoTel = tel.getText();
                nuevaFecha = fecha.getText();
                nuevoPais = pais.getText();
                nuevaCasa = casa.getText();
                nuevoGenero = (String) comboGenero.getSelectedItem();
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnActualizar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(400, 240, 130, 21);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(130, 36, 55));
		btnBuscar.setFocusable(false);
		btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String id = textID.getText();
            	if (id.length()<=0)
				{
					textID.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					 textID.setBorder(null);
				}
            	if(id.equals("12345"))
				{
            		textID.setEnabled(false);
            		btnBuscar.setVisible(false);
            		btnConsNuevo.setVisible(true);
            		btnActualizar.setVisible(true);;
            		logoHP.setVisible(true); logoCasa.setVisible(true); lblCasa.setVisible(true); lblAo.setVisible(true); lblTelfono.setVisible(true);lblPais.setVisible(true); lblFecha.setVisible(true);
            		lblCorreoElectr.setVisible(true); lblGenero.setVisible(true); labelIDA.setVisible(true); labelPS.setVisible(true);
            		nombre.setVisible(true);apellido.setVisible(true);email.setVisible(true);tel.setVisible(true);fecha.setVisible(true);pais.setVisible(true);casa.setVisible(true);
            		comboGenero.setVisible(true); 
				}else {
					JOptionPane.showMessageDialog(null, "ID de alumno no encontrado","Error", JOptionPane.WARNING_MESSAGE);
				}
            	
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnBuscar);
        
        btnConsNuevo = new JButton("Editar Nuevo");
        btnConsNuevo.setBounds(600, 240, 130, 21);
        btnConsNuevo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnConsNuevo.setForeground(new Color(255, 255, 255));
        btnConsNuevo.setBackground(new Color(130, 36, 55));
        btnConsNuevo.setFocusable(false);
        btnConsNuevo.setVisible(false);
        btnConsNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textID.setText(""); 
                textID.setEnabled(true);
                btnConsNuevo.setVisible(false);
                btnActualizar.setVisible(false);
                btnBuscar.setVisible(true);
                logoHP.setVisible(false); logoCasa.setVisible(false); lblCasa.setVisible(false); lblAo.setVisible(false); lblTelfono.setVisible(false);lblPais.setVisible(false); lblFecha.setVisible(false);
        		lblCorreoElectr.setVisible(false); lblGenero.setVisible(false); labelIDA.setVisible(false); labelPS.setVisible(false);
        		nombre.setVisible(false);apellido.setVisible(false);email.setVisible(false);tel.setVisible(false);fecha.setVisible(false);pais.setVisible(false);casa.setVisible(false);
        		nombre.setFocusable(true);apellido.setFocusable(true);email.setFocusable(true);tel.setFocusable(true);fecha.setFocusable(true);pais.setFocusable(true);casa.setFocusable(true);
                nombre.setEditable(true);apellido.setEditable(true);email.setEditable(true);tel.setEditable(true);fecha.setEditable(true);pais.setEditable(true);casa.setEditable(true); 
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnConsNuevo);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		elementosV2();
	}
	
	public void imagenes() {
		JLabel logoHog = new JLabel();
		logoHog.setIcon(new ImageIcon(getClass().getResource("castilloVector (1).png")));
		logoHog.setBounds(856,429,262,200);
	    panel.add(logoHog);
	    
	    JLabel logoHog2 = new JLabel();
		logoHog2.setIcon(new ImageIcon(getClass().getResource("castilloVector (1).png")));
		logoHog2.setBounds(-100,429,262,200);
	    panel.add(logoHog2);
	}
}
