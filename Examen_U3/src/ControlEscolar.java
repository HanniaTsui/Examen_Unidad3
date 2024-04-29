import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;

public class ControlEscolar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelMenu, panelMenuVertical, panelSur, panel;
	private JTextField nombres, textID, textNombres, textApellidos,textFecha, textEmail, textPais, textTel, nombre, apellido, fecha, pais, tel, email;
	private JPasswordField password;
	private JLabel logo,logoP, castillo, logoPerfil, labelGrisV2, labelRegistro, labelMatricula, labelBienv, labelEstudiante,labelDocente, lbConfirmacion, lblAsignatura;
	private JLabel lblCasa, lblAo, lblTelfono,lblPais, lblFecha, lblCorreoElectr,lblGenero, labelIDA,labelPS, labelEdit, labelID, labelFecha, lblMatricula, lblFoto, labelGrado;
	private JButton btn_1, btn_2, btn_3,  btn_4, btn_5, btn_6, btn_7, btnRegistrarNuevo, btnBuscar, btnConsNuevo, btnDescargar, btnFoto;
	private boolean sesionIniciada=false, actualizado=false;
	private JComboBox comboGener, comboCasa, comboGrado, comboMateria, comboAnio; 
	String nuevoNombre, nuevoApellido, nuevoEmail, nuevoTel, nuevaFecha, nuevoPais, nuevaCasa, nuevoGenero, ventanaActual = "";
	private Random rand = new Random();   
	Alumno nuevoAlumno = new Alumno();  Alumno[] listaAlumnos = new Alumno[100];
	private int indiceUltimoAlumno = -1, returnValue, indiceUltimoDocente = -1,  matricula = rand.nextInt(1000000);
	private JFileChooser selecFoto;
	Docente nuevoDocente = new Docente();  Docente[] listaDocente = new Docente[100];
    
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
		selecFoto = new JFileChooser();
        selecFoto.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selecFoto.addChoosableFileFilter(new FileNameExtensionFilter("png", "jpg"));
		inicioSesion();
		crearPersonas();
	}
	public void menu() { // Inicio - Cerrar Sesion
		contentPane.setLayout(new BorderLayout(0, 0));
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(199, 146, 66));
		panelMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panelMenu, BorderLayout.NORTH);
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
        		if(sesionIniciada)  {
		        	quitarComponentes();
		        	menuPrincipal();
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
        		quitarComponentes();
		        inicioSesion();
		        if(sesionIniciada)
		        {
		        	nombres.setText("");
		        	password.setText("");
		        }
        	}
        });
		menu.add(item2);		
		
		JMenuItem item3 = new JMenuItem("Mi perfil");
		item3.setHorizontalAlignment(SwingConstants.CENTER);
		item3.setOpaque(false);
		item3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        perfil();
        	}
        });
		menu.add(item3);
	}
	public void panel_ContentPane() {
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelMenu, BorderLayout.NORTH);	
		
		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
		contentPane.add(panel, BorderLayout.CENTER);
	}
	public void scroll() {
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
	public void inicioSesion() {
		menuI_R_A();
		panel_ContentPane();
		
		JLabel labelID = new JLabel("ID DE USUARIO",0);
		labelID.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelID.setBounds(91,207, 380, 39);
		panel.add(labelID);
		
		JLabel labelPS = new JLabel("CONTRASEÑA", SwingConstants.CENTER);
		labelPS.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelPS.setBounds(91, 346, 380, 39);
		panel.add(labelPS);
		
		password = new JPasswordField("123");
		password.setColumns(10);
		password.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		password.setBounds(131, 404, 300, 40);
		panel.add(password);
		
		nombres = new JTextField("Yo"); //Usuario "Yo"
		nombres.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		nombres.setBounds(131, 265, 300, 40);
		panel.add(nombres);
		nombres.setColumns(10);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.setBorderPainted(false);
		btnAcceder.setForeground(new Color(255, 255, 255));
		btnAcceder.setBackground(new Color(130, 36, 55));
		btnAcceder.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
						quitarComponentes();
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
		
		JLabel labelGris = new JLabel("");		labelGris.setOpaque(true); labelGris.setBackground(new Color(208, 205, 193)); labelGris.setBounds(91, 188,380,355);
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
		imagenes();
	}
	public void menuI_R_A() { //Menu recuperar, acceder
		contentPane.setLayout(new BorderLayout(0, 0));
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(199, 146, 66));
		contentPane.add(panelMenu, BorderLayout.NORTH);
		panelMenu.setLayout(new FlowLayout(FlowLayout.LEFT));		
		
		JMenuBar menu = new JMenuBar();
		menu.setOpaque(false); menu.setBorder(null);	menu.setBounds(0, 0, 240, 22);
		panelMenu.add(menu);

		
		JMenuItem item2 = new JMenuItem("Recuperar contraseña");
		item2.setHorizontalAlignment(SwingConstants.CENTER);
		item2.setOpaque(false);
		item2.addActionListener(new ActionListener()		{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        recuperar();
        	}
        });
		menu.add(item2);
				
		JMenuItem item3 = new JMenuItem("¿Cómo acceder al sistema?");
		item3.setHorizontalAlignment(SwingConstants.CENTER);
		item3.setOpaque(false);
		item3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        comoAcceder();
        	}
        });
		menu.add(item3);		
	}
	public void menuVertical()	{
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
        		quitarComponentes();
		        registrarAlumno();
        	}
        });
		menuEstudiantes.add(itemE_1);
		
		JMenuItem itemE_2 = new JMenuItem("Editar estudiante");
		itemE_2.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        editarAlumno();
        	}
        });
		menuEstudiantes.add(itemE_2);
		
		JMenuItem itemE_3 = new JMenuItem("Eliminar estudiante");
		itemE_3.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        eliminarAlumno();
        	}
        });
		menuEstudiantes.add(itemE_3);
		
		JMenuItem itemE_4 = new JMenuItem("Consultar estudiante");
		itemE_4.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
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
		itemD_1.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        registrarDocente();
        	}
        });
		menuDocentes.add(itemD_1);
		
		JMenuItem itemD_2 = new JMenuItem("Editar docente");
		itemD_2.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        editarDocente();
        	}
        });
		menuDocentes.add(itemD_2);
		
		JMenuItem itemD_3 = new JMenuItem("Eliminar docente");
		itemD_3.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        eliminarDocente();
        	}
        });
		menuDocentes.add(itemD_3);
		
		JMenuItem itemD_4 = new JMenuItem("Consultar docente");
		itemD_4.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        consultarDocente();
        	}
        });
		menuDocentes.add(itemD_4);
		
		menuBar.add(Box.createVerticalStrut(8));		
	}
	public void quitarComponentes() {
		contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
	}
	public void btnVerMatriculasAlumnos() {
		JButton btnVer = new JButton("Matrículas");
		btnVer.setBounds(400, 176, 130, 20);
		configurarBotones(btnVer);
		btnVer.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    matriculasAlumnos();
		    }
		});
		panel.add(btnVer);
	}
	public void btnVerMatriculasDocentes() {
		JButton btnVer = new JButton("Matrículas");
		btnVer.setBounds(400, 176, 130, 20);
		configurarBotones(btnVer);
		btnVer.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    matriculasDocentes();	
		    }
		});
		panel.add(btnVer);
	}
	public void matriculasAlumnos() {
		quitarComponentes();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelMenu, BorderLayout.NORTH);	
		menuVertical(); // y menu Inicio - Cerrar Sesion
		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
	    panel.setPreferredSize(new Dimension(862, 800)); 
	    int renglones = indiceUltimoAlumno + 1;
		String[][] datos = new String[renglones][3];
		for (int i = 0; i <= indiceUltimoAlumno; i++) {
			datos[i][0] = String.valueOf(listaAlumnos[i].getMatricula());
			datos[i][1] = String.valueOf(listaAlumnos[i].getNombres());
			datos[i][2] = String.valueOf(listaAlumnos[i].getApellidos());
		}
		
		String[] titulo = {"Matriculas", "Nombre", "Apellidos"};
		DefaultTableModel modelo = new DefaultTableModel(datos, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {	              
                return false; //La tabla no se edita
            }
	     };
		JTable datosTabla = new JTable(modelo);
		datosTabla.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		JScrollPane tablaScroll = new JScrollPane(datosTabla);
		tablaScroll.setBorder(BorderFactory.createLineBorder(new Color(222, 222, 222), 2));
		tablaScroll.setBounds(108, 200, 650, 300);
		panel.add(tablaScroll);
		btnVolver();
	    contentPane.add(panel);
        elementosV2();
        labelGrisV2.setBounds(58, 150, 750, 400);
	}
	public void btnVolver() {
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(368, 515, 130, 20);
		configurarBotones(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	switch (ventanaActual) {
		        case "editarAlumno":
		            quitarComponentes();
		            editarAlumno();
		            break;
		        case "eliminarAlumno":
		        	quitarComponentes();
		            eliminarAlumno();
		            break;
		        case "editarDocente":
		        	quitarComponentes();
		            editarDocente();
		            break;
		        case "eliminarDocente":
		        	quitarComponentes();
		        	eliminarDocente();
		        	break;
		    	}   
		    }
		});
		panel.add(btnVolver);
	}
	public void matriculasDocentes() {
		quitarComponentes();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelMenu, BorderLayout.NORTH);	
		menuVertical(); // y menu Inicio - Cerrar Sesion
		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
	    panel.setPreferredSize(new Dimension(862, 800)); 
	    int renglones = indiceUltimoDocente + 1;
		String[][] datos = new String[renglones][3];
		for (int i = 0; i <= indiceUltimoDocente; i++) {
			datos[i][0] = String.valueOf(listaDocente[i].getMatricula());
			datos[i][1] = String.valueOf(listaDocente[i].getNombres());
			datos[i][2] = String.valueOf(listaDocente[i].getApellidos());
		}

		String[] titulo = {"Matriculas", "Nombre", "Apellidos"};
		DefaultTableModel modelo = new DefaultTableModel(datos, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {	              
                return false; //La tabla no se edita
            }
	     };
		JTable datosTabla = new JTable(modelo);
		datosTabla.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		JScrollPane tablaScroll = new JScrollPane(datosTabla);
		tablaScroll.setBorder(BorderFactory.createLineBorder(new Color(222, 222, 222), 2));
		tablaScroll.setBounds(108, 200, 650, 300);
		panel.add(tablaScroll);				
	    contentPane.add(panel);
	    btnVolver();
        elementosV2();
        labelGrisV2.setBounds(58, 150, 750, 400);
	}
	public void btnListaAlumnos() {
		JButton btnListarAlumnos = new JButton("Listar Alumnos");
		btnListarAlumnos.setBounds(400, 176, 130, 20);
		configurarBotones(btnListarAlumnos);
		btnListarAlumnos.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	StringBuilder lista = new StringBuilder("Lista de Alumnos:\n\n");
			    for (int i = 0; i <= indiceUltimoAlumno; i++) {
			        lista.append("ID: ").append(listaAlumnos[i].getMatricula()).append("\n");
			        lista.append("Nombre: ").append(listaAlumnos[i].getNombres()).append("\n");
			        lista.append("Apellido: ").append(listaAlumnos[i].getApellidos()).append("\n");
			        lista.append("Email: ").append(listaAlumnos[i].getEmail()).append("\n");
			        lista.append("Teléfono: ").append(listaAlumnos[i].getTelefono()).append("\n");
			        lista.append("Fecha de Nacimiento: ").append(listaAlumnos[i].getFechaNacimiento()).append("\n");
			        lista.append("País de Nacimiento: ").append(listaAlumnos[i].getPaisNacimiento()).append("\n");
			        lista.append("Género: ").append(listaAlumnos[i].getGenero()).append("\n");
			        lista.append("Casa: ").append(listaAlumnos[i].getCasa()).append("\n");
			        lista.append("Año: ").append(listaAlumnos[i].getAnio()).append("\n\n");
			    }
			    JTextArea textArea = new JTextArea(lista.toString());
			    textArea.setEditable(false);
			    JScrollPane scrollPane = new JScrollPane(textArea);
			    scrollPane.setPreferredSize(new Dimension(400, 400)); 
			    JOptionPane.showMessageDialog(null, scrollPane, "Lista de Alumnos", JOptionPane.INFORMATION_MESSAGE);
		    }
		});
		panel.add(btnListarAlumnos);
	}
	public void btnListaDocentes() {
		JButton btnListar = new JButton("Listar Docentes");
		btnListar.setBounds(400, 176, 130, 20);
		configurarBotones(btnListar);
		btnListar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	StringBuilder lista = new StringBuilder("Lista de Docentes:\n\n");
			    for (int i = 0; i <= indiceUltimoDocente; i++) {
			        lista.append("ID: ").append(listaDocente[i].getMatricula()).append("\n");
			        lista.append("Nombre: ").append(listaDocente[i].getNombres()).append("\n");
			        lista.append("Apellido: ").append(listaDocente[i].getApellidos()).append("\n");
			        lista.append("Email: ").append(listaDocente[i].getEmail()).append("\n");
			        lista.append("Teléfono: ").append(listaDocente[i].getTelefono()).append("\n");
			        lista.append("Fecha de Nacimiento: ").append(listaDocente[i].getFechaNacimiento()).append("\n");
			        lista.append("País de Nacimiento: ").append(listaDocente[i].getPaisNacimiento()).append("\n");
			        lista.append("Género: ").append(listaDocente[i].getGenero()).append("\n");
			        lista.append("Casa: ").append(listaDocente[i].getCasa()).append("\n");
			        lista.append("Grado: ").append(listaDocente[i].getGrado()).append("\n");
			        lista.append("Asignatura: ").append(listaDocente[i].getMateria()).append("\n\n");
			        
			    }
			    JTextArea textArea = new JTextArea(lista.toString());
			    textArea.setEditable(false);
			    JScrollPane scrollPane = new JScrollPane(textArea);
			    scrollPane.setPreferredSize(new Dimension(400, 400)); 
			    JOptionPane.showMessageDialog(null, scrollPane, "Lista de Alumnos", JOptionPane.INFORMATION_MESSAGE);
		    }
		});
		panel.add(btnListar);
	}
	public void menuPrincipal() {
		menu();
		panel_ContentPane();
		elementos();
		labelBienv = new JLabel("Bienvenido, ¿qué desea realizar?",0);
		labelBienv.setForeground(new Color(217, 217, 217));
		labelBienv.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelBienv.setBounds(0, 110, 996, 30);
		panel.add(labelBienv);
		
		labelEstudiante = new JLabel("ESTUDIANTE" ,0);
		configurarLabels(labelEstudiante);
		labelEstudiante.setBounds(155, 150, 314, 35);
		panel.add(labelEstudiante);
		
		labelDocente = new JLabel("DOCENTE", SwingConstants.CENTER);
		configurarLabels(labelDocente);
		labelDocente.setBounds(535, 150, 314, 35);
		panel.add(labelDocente);
		
		JButton btn = new JButton("Registrar alumno");
		configurarBotones(btn);
		btn.setBounds(150, 330, 140, 30);
		btn.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        registrarAlumno();
        	}
        });
		panel.add(btn);		
		btn_1 = new JButton("Editar alumno");
		configurarBotones(btn_1);
		btn_1.setBounds(334, 330, 140, 30);
		btn_1.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        editarAlumno();
        	}
        });
		panel.add(btn_1);
	    
		btn_2 = new JButton("Consultar alumno");
		configurarBotones(btn_2);
		btn_2.setBounds(150, 500, 140, 30);
		btn_2.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        consultarAlumno();
        	}
        });
		panel.add(btn_2);
		
		btn_3 = new JButton("Eliminar alumno");
		configurarBotones(btn_3);
		btn_3.setBounds(334, 500, 140, 30);
		btn_3.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        eliminarAlumno();
        	}
        });
		panel.add(btn_3);
		
		btn_4 = new JButton("Registrar docente");
		configurarBotones(btn_4);
		btn_4.setBounds(530, 330, 140, 30);
		btn_4.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        registrarDocente();
        	}
        });
		panel.add(btn_4);
		
		btn_5 = new JButton("Editar docente");
		configurarBotones(btn_5);
		btn_5.setBounds(714, 330, 140, 30);
		btn_5.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        editarDocente();
        	}
        });
		panel.add(btn_5);
		
		btn_6 = new JButton("Consultar docente");
		configurarBotones(btn_6);
		btn_6.setBounds(530, 500, 140, 30);
		btn_6.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        consultarDocente();
        	}
        });
		panel.add(btn_6);
		
		btn_7 = new JButton("Eliminar docente");
		configurarBotones(btn_7);
		btn_7.setBounds(714, 500, 140, 30);
		btn_7.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        eliminarDocente();
        	}
        });
		panel.add(btn_7);
		imagenes();
		String[] imagenes = {"registrar.png", "registrarDocente.png", "editar.png", "editarDocente.png", "consultar.png", "eliminar.png", "consultarDocente.png", "eliminarDocente.png"};
		int[][] bounds = {{160, 210}, {536, 210}, {340, 210}, {720, 210}, {156, 385}, {340, 385}, {536, 385}, {720, 385}};
		int labelW = 128;
		int labelH = 128;

		for (int i = 0; i < imagenes.length; i++) {
		    JLabel label = new JLabel();
		    label.setIcon(new ImageIcon(getClass().getResource(imagenes[i])));
		    label.setBounds(bounds[i][0], bounds[i][1], labelW, labelH);
		    panel.add(label);
		}
		
		int[] boundsX = {155, 339, 535, 719};
		int[] boundsY = {210, 385};
		int labelWidth = 130;
		int labelHeight = 126;

		for (int i = 0; i < boundsX.length; i++) {
		    for (int j = 0; j < boundsY.length; j++) {
		        JLabel labelGris = new JLabel("");
		        labelGris.setOpaque(true);
		        labelGris.setBackground(new Color(208, 205, 193));
		        labelGris.setBounds(boundsX[i], boundsY[j], labelWidth, labelHeight);
		        panel.add(labelGris);
		    }
		}
	}
	public void configurarEtiquetas(JLabel componente) {
		componente.setForeground(new Color(128, 0, 0));
	    componente.setFont(new Font("Tahoma", Font.BOLD, 16));
	}
	public void configurarLabels(JLabel componente) { //Para menu principal{
		componente.setForeground(new Color(0, 0, 0));
		componente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		componente.setOpaque(true);
		componente.setBackground(new Color(212, 175, 55));
	}
	public void configurarBotones(JButton componente) {
	    componente.setForeground(Color.WHITE);
	    componente.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    componente.setFocusable(false);
	    componente.setBackground(new Color(130, 36, 55));
	}
	public void elementos()	{
		JLabel labelEscuela = new JLabel("Colegio Hogwarts de Magia y Hechiceria");
		labelEscuela.setBounds(166, 30, 753, 80);
		labelEscuela.setForeground(Color.white);
		labelEscuela.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 37));
		panel.add(labelEscuela);
		
		logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("escudo.png")));
		logo.setBounds(51,32,100,100);
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
		
		labelGrisV2 = new JLabel("");
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
	public void panel() {
		panel = new JPanel();      
		panel.setLayout(null);
		panel.setBackground(new Color(82, 24, 37));
	    panel.setPreferredSize(new Dimension(862, 800)); 
	}
	public void consultarAlumno() {
		panel(); 
	    menuVertical();
	    
	    JLabel logoP = new JLabel();
	    logoP.setVisible(false);
	    logoP.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
	    logoP.setBounds(643,290,115,130);
	    panel.add(logoP);
	    
	    JLabel logo = new JLabel(); 
	    logo.setIcon(new ImageIcon(getClass().getResource("escudo.png")));
	    logo.setBounds(643,535,115,130);
	    panel.add(logo);
	    
	    JLabel  labelCons= new JLabel("Consultar alumno");
	    labelCons.setVerticalAlignment(SwingConstants.TOP);
	    labelCons.setForeground(new Color(0, 0, 0));
	    labelCons.setFont(new Font("Tahoma", Font.BOLD, 20));
	    labelCons.setBounds(102, 170, 687, 30);
		panel.add(labelCons);
		btnListaAlumnos();
		JLabel labelID = new JLabel("ID del alumno: ", SwingConstants.LEFT);
		configurarEtiquetas(labelID);
		labelID.setBounds(102, 212, 150, 13);
		panel.add(labelID);
	    lblAo = new JLabel("Año: ", SwingConstants.LEFT);
		lblAo.setForeground(new Color(128, 0, 0));
		lblAo.setVisible(false);
	    lblAo.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblAo.setBounds(102, 639, 180, 20);
	    panel.add(lblAo);
	    
	    comboAnio = new JComboBox();
	    comboAnio.setModel(new DefaultComboBoxModel(new String[] {"  ", "1", "2","3", "4","5","6", "7"}));
	    comboAnio.setBounds(171, 639, 159, 20);
	    comboAnio.setVisible(false);
	    panel.add(comboAnio);
	
		elementosLabels();
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(400, 240, 130, 21);
		configurarBotones(btnBuscar);
		desactivarBoton();
		btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String id = textID.getText();
            	boolean matriculaEncontrada = false;
            	if (id.length()<=0)
				{
            		textID.setBorder(BorderFactory.createLineBorder(Color.RED));	
				} else {
					 textID.setBorder(null);
				}
            	for (int i = 0; i <= indiceUltimoAlumno; i++) {
            	    if (listaAlumnos[i].getMatricula() == Integer.parseInt(id)) {
                    nombre.setText(listaAlumnos[i].getNombres());
                    apellido.setText(listaAlumnos[i].getApellidos());
                    email.setText(listaAlumnos[i].getEmail());
                    tel.setText(listaAlumnos[i].getTelefono());
                    fecha.setText(listaAlumnos[i].getFechaNacimiento());
                    pais.setText(listaAlumnos[i].getPaisNacimiento());
                    comboCasa.setSelectedItem(listaAlumnos[i].getCasa());
                    comboAnio.setSelectedItem(listaAlumnos[i].getAnio());
                    comboGener.setSelectedItem(listaAlumnos[i].getGenero());             
                    
                   componentesConsultar();
                   logoP.setVisible(true); lblAo.setVisible(true);  comboAnio.setEnabled(false); comboAnio.setVisible(true);
                    String rutaFoto = listaAlumnos[i].getRutaFoto();
                    if (rutaFoto != null && !rutaFoto.isEmpty()) {
                        ImageIcon fotoAlumno = new ImageIcon(rutaFoto);
                        logoP.setIcon(fotoAlumno);
                    } else {
                    	logoPerfil.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
                    }
                    matriculaEncontrada = true;
                    break;
            	    }    
            	} if(!matriculaEncontrada) {
					JOptionPane.showMessageDialog(null, "ID de alumno no encontrado","Error", JOptionPane.WARNING_MESSAGE);
				}

                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnBuscar);
        
        btnConsNuevo = new JButton("Consultar Nuevo");
        btnConsNuevo.setBounds(600, 240, 130, 21);
        configurarBotones(btnConsNuevo);
        btnConsNuevo.setVisible(false);
        btnConsNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textID.setText("");   textID.setEnabled(true);
                btnConsNuevo.setVisible(false);
                btnDescargar.setVisible(false);
                btnBuscar.setVisible(true);
                desactivarBoton();
                componentesConsultarV2(); 
                logoP.setVisible(false);  lblAo.setVisible(false);  comboAnio.setVisible(false);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnConsNuevo);
        
        btnDescargar = new JButton("Descargar");
        btnDescargar.setBounds(400, 240, 130, 21);
        configurarBotones(btnDescargar);
        btnDescargar.setVisible(false);
        btnDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDescargar.setVisible(true);
                JOptionPane.showInternalMessageDialog(null, "Su descarga ha sido exitosa", "Descarga exitosa", JOptionPane.INFORMATION_MESSAGE);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnDescargar);
		scroll();
		elementosV2();
	}
	public void consultarDocente() {
		panel();
	    menuVertical();
	    
	    logoP = new JLabel();
	    logoP.setVisible(false);
	    logoP.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
	    logoP.setBounds(643,290,115,130);
	    panel.add(logoP);
	    
	    JLabel logo = new JLabel(); 
	    logo.setIcon(new ImageIcon(getClass().getResource("escudo.png")));
	    logo.setBounds(643,535,115,130);
	    panel.add(logo);
	    
	    JLabel  labelCons= new JLabel("Consultar docente");
	    labelCons.setVerticalAlignment(SwingConstants.TOP);
	    labelCons.setForeground(new Color(0, 0, 0));
	    labelCons.setFont(new Font("Tahoma", Font.BOLD, 20));
	    labelCons.setBounds(102, 170, 687, 30);
		panel.add(labelCons);
		JLabel labelID = new JLabel("ID del docente: ", SwingConstants.LEFT);
		configurarEtiquetas(labelID);
		labelID.setBounds(102, 212, 150, 13);
		panel.add(labelID);
		btnListaDocentes();
		labelGrado = new JLabel("Grado: *");
		configurarEtiquetas(labelGrado);
	    labelGrado.setBounds(102, 639, 180, 20); labelGrado.setVisible(false);
	    panel.add(labelGrado);
	    
	    
	    lblAsignatura = new JLabel("Asignatura: *", SwingConstants.LEFT);
	    configurarEtiquetas(lblAsignatura); lblAsignatura.setVisible(false);
	    lblAsignatura.setBounds(102, 690, 180, 20);
	    panel.add(lblAsignatura);
	    
	    comboGrado = new JComboBox();
	    comboGrado.setModel(new DefaultComboBoxModel(new String[] {"  ", "Media Superior", "Licenciatura","Maestría", "Doctorado"}));
	    comboGrado.setBounds(185, 639, 145, 21);
	    comboGrado.setVisible(false);
	    panel.add(comboGrado);
	    
	    logoPerfil = new JLabel();
	    
	    comboMateria = new JComboBox();
	    comboMateria.setModel(new DefaultComboBoxModel(new String[] {"  ", "Transformaciones","Historia de la magia", "Herbología", "Pociones", "Encantamientos", "Astronomia"}));
	    comboMateria.setBounds(225, 690, 225, 20); comboMateria.setVisible(false);
	    panel.add(comboMateria);
	    
	    elementosLabels();
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(400, 240, 130, 21);
		configurarBotones(btnBuscar);
		desactivarBoton();
		btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String id = textID.getText();
            	boolean matriculaEncontrada = false;
            	if (id.length()<=0)
				{
            		JOptionPane.showMessageDialog(null, "Por favor ingrese una matrícula", "Error", JOptionPane.WARNING_MESSAGE);
					textID.setBorder(BorderFactory.createLineBorder(Color.RED));	
				} else {
					 textID.setBorder(null);
				}
            	for (int i = 0; i <= indiceUltimoDocente; i++) {
            	    if (listaDocente[i].getMatricula() == Integer.parseInt(id)) {
                    nombre.setText(listaDocente[i].getNombres());
                    apellido.setText(listaDocente[i].getApellidos());
                    email.setText(listaDocente[i].getEmail());
                    tel.setText(listaDocente[i].getTelefono());
                    fecha.setText(listaDocente[i].getFechaNacimiento());
                    pais.setText(listaDocente[i].getPaisNacimiento());
                    comboCasa.setSelectedItem(listaDocente[i].getCasa());
                    comboGener.setSelectedItem(listaDocente[i].getGenero());  
                    comboMateria.setSelectedItem(listaDocente[i].getMateria());
                    comboGrado.setSelectedItem(listaDocente[i].getGrado());
                  
                   componentesConsultar();
                   logoP.setVisible(true); labelGrado.setVisible(true);  lblAsignatura.setVisible(true); comboGrado.setVisible(true);  comboMateria.setVisible(true); comboGrado.setEnabled(false); comboMateria.setEnabled(false); 
               	
                   String rutaFoto = listaDocente[i].getRutaFoto();
                   if (rutaFoto != null && !rutaFoto.isEmpty()) {
                       ImageIcon fotoAlumno = new ImageIcon(rutaFoto);
                       logoP.setIcon(fotoAlumno);
                   } else {
                   	logoPerfil.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
                   }
                    matriculaEncontrada = true;
                    break;
            	    }    
            	} if(!matriculaEncontrada) {
					JOptionPane.showMessageDialog(null, "ID de docente no encontrado","Error", JOptionPane.WARNING_MESSAGE);
				}

                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnBuscar);
        
        btnConsNuevo = new JButton("Consultar Nuevo");
        btnConsNuevo.setBounds(600, 240, 130, 21);
        configurarBotones(btnConsNuevo);
        btnConsNuevo.setVisible(false);
        btnConsNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textID.setText("");   textID.setEnabled(true);
                btnConsNuevo.setVisible(false);
                btnDescargar.setVisible(false);
                btnBuscar.setVisible(true);
                desactivarBoton();
                componentesConsultarV2();
                logoP.setVisible(false); labelGrado.setVisible(false); comboGrado.setVisible(false); comboMateria.setVisible(false);lblAsignatura.setVisible(false);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnConsNuevo);
        
        btnDescargar = new JButton("Descargar");
        btnDescargar.setBounds(400, 240, 130, 21);
        configurarBotones(btnDescargar);
        btnDescargar.setVisible(false);
        btnDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDescargar.setVisible(true);
                JOptionPane.showInternalMessageDialog(null, "Su descarga ha sido exitosa", "Descarga exitosa", JOptionPane.INFORMATION_MESSAGE);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnDescargar);

		scroll();	
		labelGrisDocente();
		elementosV2();	
	}
	public void componentesConsultar() { //Buscar
		textID.setEnabled(false);
		btnBuscar.setVisible(false);
		btnConsNuevo.setVisible(true);
		btnDescargar.setVisible(true);
		lblCasa.setVisible(true);  lblTelfono.setVisible(true);lblPais.setVisible(true); lblFecha.setVisible(true);
		lblCorreoElectr.setVisible(true);  comboGener.setVisible(true); lblGenero.setVisible(true); labelIDA.setVisible(true); labelPS.setVisible(true);
        nombre.setVisible(true);  apellido.setVisible(true); email.setVisible(true); tel.setVisible(true); fecha.setVisible(true); pais.setVisible(true);   comboCasa.setVisible(true);   comboGener.setVisible(true);
        nombre.setFocusable(false); nombre.setOpaque(false); nombre.setBorder(null);   apellido.setFocusable(false); apellido.setOpaque(false); apellido.setBorder(null);
        email.setFocusable(false); email.setOpaque(false); email.setBorder(null);tel.setFocusable(false); tel.setOpaque(false); tel.setBorder(null);
        fecha.setFocusable(false); fecha.setOpaque(false); fecha.setBorder(null);pais.setFocusable(false); pais.setOpaque(false); pais.setBorder(null);
        comboCasa.setEnabled(false);  comboGener.setEnabled(false);         
	}
	public void componentesConsultarV2() { //consultar nuevo
		lblCasa.setVisible(false);lblTelfono.setVisible(false);lblPais.setVisible(false); lblFecha.setVisible(false);
		lblCorreoElectr.setVisible(false);  comboGener.setVisible(false); lblGenero.setVisible(false); labelIDA.setVisible(false); labelPS.setVisible(false);
		nombre.setVisible(false);apellido.setVisible(false);email.setVisible(false);tel.setVisible(false);fecha.setVisible(false);pais.setVisible(false);comboCasa.setVisible(false);
	}
	public void perfil()	{
		menu();
		panel_ContentPane();
		
		JLabel lblDetallesDeUsuario = new JLabel("Detalles de usuario:");
		lblDetallesDeUsuario.setForeground(Color.BLACK);
		lblDetallesDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDetallesDeUsuario.setBounds(225, 316, 460, 30);
		panel.add(lblDetallesDeUsuario);
		
		JLabel logoHog = new JLabel();
		logoHog.setIcon(new ImageIcon(getClass().getResource("sombrero.png")));
		logoHog.setBounds(443,176,110,130);
	    panel.add(logoHog);
		
		JLabel lblInstrucciones = new JLabel("Usuario: Yo");
	    lblInstrucciones.setForeground(Color.black);
	    lblInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 20));
	    lblInstrucciones.setBounds(311, 176, 122, 30);
	    panel.add(lblInstrucciones);

	    JTextArea txtDatos = new JTextArea();
	    txtDatos.setWrapStyleWord(true);
	    txtDatos.setFocusable(false);
	    txtDatos.setForeground(new Color(130, 36, 55));
	    txtDatos.setFont(new Font("Tahoma", Font.PLAIN,19));
	    txtDatos.setLineWrap(true);
	    txtDatos.setBounds(283, 345, 243, 180);
	    txtDatos.setText("Nombre: \nCorreo electrónico:\nTeléfono: \n" +
	                     "País: \n" +
	                     "Ocupación:\n\nProximos eventos: ");
	    txtDatos.setOpaque(false);
	    txtDatos.setEditable(false);
	    panel.add(txtDatos);
	    
	    JTextArea txtRes = new JTextArea();
	    txtRes.setWrapStyleWord(true);
	    txtRes.setFocusable(false);
	    txtRes.setForeground(Color.black);
	    txtRes.setFont(new Font("Tahoma", Font.PLAIN, 19));
	    txtRes.setLineWrap(true);
	    txtRes.setBounds(499, 345, 262, 180);
	    txtRes.setText("Yo\n"+ "yo@wizardingworld.com \n9809809800\n" +
	                     "México\n" +
	                     "Administrador\n\nCeremonia de Selección");
	    txtRes.setOpaque(false);
	    txtRes.setEditable(false);
	    panel.add(txtRes);
	    
	    JButton btnAcceder = new JButton("Aceptar");
		configurarBotones(btnAcceder);
		btnAcceder.setBounds(438, 524, 120, 30);
		btnAcceder.addActionListener(new ActionListener()
		{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        menuPrincipal();
			}});      
		panel.add(btnAcceder);
	    
	    JLabel labelGrisV2 = new JLabel("");
		labelGrisV2.setBackground(new Color(208, 205, 193));
		labelGrisV2.setOpaque(true);
		labelGrisV2.setBounds(183, 150, 630, 414);
		panel.add(labelGrisV2);
		
		elementos();
		imagenes();
	}
	public void recuperar() { //Recuperar contraseña
		menuI_R_A();
		panel_ContentPane();
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
		btnBus.setForeground(new Color(255, 255, 255));
		btnBus.setBackground(new Color(130, 36, 55));
		btnBus.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
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
				        contentPane.revalidate(); contentPane.repaint();
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
		panel_ContentPane();
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
		labelGris.setOpaque(true); labelGris.setBackground(new Color(208, 205, 193));
		labelGris.setBounds(272, 250,452,235);
		panel.add(labelGris);
		
		elementos();
	}
	public void eliminarAlumno() {
		panel(); 
	    menuVertical();
	    ventanaActual = "eliminarAlumno";
	    JLabel labelRegistro = new JLabel("Eliminar alumno");
		labelRegistro.setVerticalAlignment(SwingConstants.TOP);
		labelRegistro.setForeground(new Color(0, 0, 0));
		labelRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegistro.setBounds(102, 170, 687, 30);
		panel.add(labelRegistro);

		JLabel labelID = new JLabel("ID del alumno: ", SwingConstants.LEFT);
		configurarEtiquetas(labelID);
		labelID.setBounds(102, 212, 150, 13);
		panel.add(labelID);
		btnVerMatriculasAlumnos();
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
		
		lbConfirmacion = new JLabel();
		lbConfirmacion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbConfirmacion.setHorizontalAlignment(SwingConstants.CENTER);
		lbConfirmacion.setBounds(215, 336, 450, 20);
		lbConfirmacion.setVisible(false);
		panel.add(lbConfirmacion);
		
		btnBuscar = new JButton("Eliminar");
		btnBuscar.setBounds(400, 240, 130, 21);
		configurarBotones(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String id = textID.getText();
            	if (id.length()<=0)
				{
					textID.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					 textID.setBorder(null);
					 int matriculaEliminar = Integer.parseInt(id);
			            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar a este alumno?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
			            if (opcion == JOptionPane.YES_OPTION) {
			                boolean alumnoEncontrado = false;
			                for (int i = 0; i <= indiceUltimoAlumno; i++) {
			                    if (listaAlumnos[i].getMatricula() == matriculaEliminar) {
			                        alumnoEncontrado = true;
			                        // Eliminar al alumno de la lista
			                        for (int j = i; j < indiceUltimoAlumno; j++) {
			                            listaAlumnos[j] = listaAlumnos[j + 1];
			                        }
			                        indiceUltimoAlumno--;
			                        break;
			                    }
			                }
			                if (alumnoEncontrado) {
			                    textID.setEnabled(false);
			                    btnBuscar.setVisible(false);
			                    lbConfirmacion.setText("Alumno con ID '" + id + "' eliminado con éxito");
			                    lbConfirmacion.setVisible(true);
			                    btnConsNuevo.setVisible(true);
			                } else {
			                    JOptionPane.showMessageDialog(null, "ID no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
			                }
			            }
			        }
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnBuscar);
        
        btnConsNuevo = new JButton("Eliminar Nuevo");
        btnConsNuevo.setBounds(600, 240, 130, 21);
        configurarBotones(btnConsNuevo);
        btnConsNuevo.setVisible(false);
        btnConsNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textID.setText("");  textID.setEnabled(true);
                panel.remove(btnConsNuevo);
                btnBuscar.setVisible(true);
                desactivarBoton();
                lbConfirmacion.setVisible(false);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnConsNuevo); 
        scroll();
        elementosV2();
	}
	public void eliminarDocente() {
		panel(); 
	    menuVertical();
	    ventanaActual = "eliminarDocente";
	    JLabel labelRegistro = new JLabel("Eliminar docente");
		labelRegistro.setVerticalAlignment(SwingConstants.TOP);
		labelRegistro.setForeground(new Color(0, 0, 0));
		labelRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegistro.setBounds(102, 170, 687, 30);
		panel.add(labelRegistro);	
		
		JLabel labelID = new JLabel("ID del docente: ", SwingConstants.LEFT);
		configurarEtiquetas(labelID);
		labelID.setBounds(102, 212, 150, 13);
		panel.add(labelID);
		btnVerMatriculasDocentes();
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
		
		lbConfirmacion = new JLabel();
		lbConfirmacion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbConfirmacion.setHorizontalAlignment(SwingConstants.CENTER);
		lbConfirmacion.setBounds(215, 336, 450, 20);
		lbConfirmacion.setVisible(false);
		panel.add(lbConfirmacion);
		
		btnBuscar = new JButton("Eliminar");
		btnBuscar.setBounds(400, 240, 130, 21);
		configurarBotones(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String id = textID.getText();
            	if (id.length()<=0)
				{
					textID.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					 textID.setBorder(null);
					 int matriculaEliminar = Integer.parseInt(id);
			            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar a este docente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
			            if (opcion == JOptionPane.YES_OPTION) {
			                boolean docenteEncontrado = false;
			                for (int i = 0; i <= indiceUltimoDocente; i++) {
			                    if (listaDocente[i].getMatricula() == matriculaEliminar) {
			                    	docenteEncontrado = true;
			                        for (int j = i; j < indiceUltimoDocente; j++) {
			                        	listaDocente[j] = listaDocente[j + 1];
			                        }
			                        indiceUltimoDocente--;
			                        break;
			                    }
			                }
			                if (docenteEncontrado) {
			                    textID.setEnabled(false);
			                    btnBuscar.setVisible(false);
			                    lbConfirmacion.setText("Docente con ID '" + id + "' eliminado con éxito");
			                    lbConfirmacion.setVisible(true);
			                    btnConsNuevo.setVisible(true);
			                } else {
			                    JOptionPane.showMessageDialog(null, "ID no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
			                }
			            }
			        }
                panel.revalidate();
                panel.repaint();
            }
        });
		
		JLabel logo = new JLabel(); 
	    logo.setIcon(new ImageIcon(getClass().getResource("escudo.png")));
	    logo.setBounds(383,534,100,130);
	    panel.add(logo);
	    
        panel.add(btnBuscar);	
        btnConsNuevo = new JButton("Eliminar Nuevo");
        btnConsNuevo.setBounds(600, 240, 130, 21);
        configurarBotones(btnConsNuevo);
        btnConsNuevo.setVisible(false);
        btnConsNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textID.setText(""); 
                textID.setEnabled(true);
                panel.remove(btnConsNuevo);
                desactivarBoton();
                btnBuscar.setVisible(true);
                lbConfirmacion.setVisible(false);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnConsNuevo); 
	    
        scroll();
        elementosV2();
	}
	public void editarAlumno() {
		panel();
	    menuVertical();
	    ventanaActual = "editarAlumno";
	    labelEdit= new JLabel("Editar alumno");
	    labelEdit.setVerticalAlignment(SwingConstants.TOP);
	    labelEdit.setForeground(new Color(0, 0, 0));
	    labelEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
	    labelEdit.setBounds(102, 170, 687, 30);
		panel.add(labelEdit);
		JLabel labelID = new JLabel("ID del alumno: ", SwingConstants.LEFT);
		configurarEtiquetas(labelID);
		labelID.setBounds(102, 212, 150, 13);
		panel.add(labelID);
		
		JLabel logoP = new JLabel();
	    logoP.setVisible(false);
	    logoP.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
	    logoP.setBounds(643,290,115,130);
	    panel.add(logoP);
	    
	    JLabel logo = new JLabel(); 
	    logo.setIcon(new ImageIcon(getClass().getResource("escudo.png")));
	    logo.setBounds(643,535,115,130);
	    panel.add(logo);
	    
	    lblAo = new JLabel("Año: ", SwingConstants.LEFT);
	    configurarEtiquetas(lblAo);
	    lblAo.setVisible(false);
	    lblAo.setBounds(102, 639, 180, 20);
	    panel.add(lblAo);
		
	    comboAnio = new JComboBox();
	    comboAnio.setModel(new DefaultComboBoxModel(new String[] {"  ", "1", "2","3", "4","5","6", "7"}));
	    comboAnio.setBounds(171, 639, 159, 20);
	    comboAnio.setVisible(false);
	    panel.add(comboAnio);
		elementosLabels();
		btnVerMatriculasAlumnos();
		JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(400, 240, 130, 21);
        configurarBotones(btnActualizar);
        btnActualizar.setVisible(false);
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnActualizar.setVisible(false);
                componentesEditar();comboAnio.setEditable(false); comboAnio.setEnabled(false);
                actualizado=true; 
                String id = textID.getText();
                for (int i = 0; i <= indiceUltimoAlumno; i++) {
                    if (listaAlumnos[i].getMatricula() == Integer.parseInt(id)) {
                        listaAlumnos[i].setNombres(nombre.getText());
                        listaAlumnos[i].setApellidos(apellido.getText());
                        listaAlumnos[i].setEmail(email.getText());
                        listaAlumnos[i].setTelefono(tel.getText());
                        listaAlumnos[i].setFechaNacimiento(fecha.getText());
                        listaAlumnos[i].setPaisNacimiento(pais.getText());
                        listaAlumnos[i].setAnio((String) comboAnio.getSelectedItem());
                        listaAlumnos[i].setCasa((String) comboCasa.getSelectedItem());
                        listaAlumnos[i].setGenero((String) comboGener.getSelectedItem());
                        break; 
                    }
                }
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnActualizar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(400, 240, 130, 21);
		configurarBotones(btnBuscar);
		desactivarBoton(); // btnBuscar
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
            	boolean matriculaEncontrada = false; 
            	for (int i = 0; i <= indiceUltimoAlumno; i++) {
            	    if (listaAlumnos[i].getMatricula() == Integer.parseInt(id)) {
                    nombre.setText(listaAlumnos[i].getNombres());
                    apellido.setText(listaAlumnos[i].getApellidos());
                    email.setText(listaAlumnos[i].getEmail());
                    tel.setText(listaAlumnos[i].getTelefono());
                    fecha.setText(listaAlumnos[i].getFechaNacimiento());
                    pais.setText(listaAlumnos[i].getPaisNacimiento());
                    comboCasa.setSelectedItem(listaAlumnos[i].getCasa());
                    comboAnio.setSelectedItem(listaAlumnos[i].getAnio());
                    comboGener.setSelectedItem(listaAlumnos[i].getGenero());
                    
                   componentesEditarV2(); btnActualizar.setVisible(true); logoP.setVisible(true); lblAo.setVisible(true);  comboAnio.setVisible(true); 
                   String rutaFoto = listaAlumnos[i].getRutaFoto();
                    if (rutaFoto != null && !rutaFoto.isEmpty()) {
                        ImageIcon fotoAlumno = new ImageIcon(rutaFoto);
                        logoP.setIcon(fotoAlumno);
                    } else {
                    	logoPerfil.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
                    }
                    matriculaEncontrada = true;
                    break;
            	    }    
            	} if(!matriculaEncontrada) {
					JOptionPane.showMessageDialog(null, "ID de alumno no encontrado","Error", JOptionPane.WARNING_MESSAGE);
				}
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnBuscar);
        
        btnConsNuevo = new JButton("Editar Nuevo");
        btnConsNuevo.setBounds(600, 240, 130, 21);
        configurarBotones(btnConsNuevo);
        btnConsNuevo.setVisible(false);
        btnConsNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	componentesEditarV3(); btnActualizar.setVisible(false);  logoP.setVisible(false); lblAo.setVisible(false);comboAnio.setVisible(false);
            	desactivarBoton();
            	panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnConsNuevo);

		scroll();
		elementosV2();
	}
	public void componentesEditarV2() { //buscar
		textID.setEnabled(false);
		btnBuscar.setVisible(false);
		btnConsNuevo.setVisible(true);
		lblCasa.setVisible(true);  lblTelfono.setVisible(true);lblPais.setVisible(true); lblFecha.setVisible(true);
		lblCorreoElectr.setVisible(true);  comboGener.setVisible(true); lblGenero.setVisible(true); labelIDA.setVisible(true); labelPS.setVisible(true); 
        nombre.setVisible(true);  apellido.setVisible(true); email.setVisible(true); tel.setVisible(true); fecha.setVisible(true); pais.setVisible(true);   comboCasa.setVisible(true);  comboGener.setVisible(true);       
    }
	public void componentesEditar() {	// Actualizar	 
         nombre.setFocusable(false);apellido.setFocusable(false);email.setFocusable(false);tel.setFocusable(false);fecha.setFocusable(false);pais.setFocusable(false);comboCasa.setFocusable(false); comboGener.setEnabled(false); 
         nombre.setEditable(false);apellido.setEditable(false);email.setEditable(false);tel.setEditable(false);fecha.setEditable(false);pais.setEditable(false);comboCasa.setEditable(false); comboCasa.setEnabled(false);  
	}
	public void componentesEditarV3() { //editarNuevo
		textID.setText(""); 
        textID.setEnabled(true);
        btnConsNuevo.setVisible(false);  
        btnBuscar.setVisible(true);
        lblCasa.setVisible(false); lblTelfono.setVisible(false);lblPais.setVisible(false); lblFecha.setVisible(false);
		lblCorreoElectr.setVisible(false); lblGenero.setVisible(false); labelIDA.setVisible(false); labelPS.setVisible(false); comboCasa.setEnabled(true); comboGener.setEnabled(true);
		nombre.setVisible(false);apellido.setVisible(false);email.setVisible(false);tel.setVisible(false);fecha.setVisible(false);pais.setVisible(false);comboCasa.setVisible(false);
		nombre.setFocusable(true);apellido.setFocusable(true);email.setFocusable(true);tel.setFocusable(true);fecha.setFocusable(true);pais.setFocusable(true);
        nombre.setEditable(true);apellido.setEditable(true);email.setEditable(true);tel.setEditable(true);fecha.setEditable(true);pais.setEditable(true); comboGener.setVisible(false); 
	}
	public void editarDocente() {
		panel();
	    menuVertical();
	    ventanaActual = "editarDocente";
	    labelEdit= new JLabel("Editar docente");
	    labelEdit.setVerticalAlignment(SwingConstants.TOP);
	    labelEdit.setForeground(new Color(0, 0, 0));
	    labelEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
	    labelEdit.setBounds(102, 170, 687, 30);
		panel.add(labelEdit);
		
		JLabel labelID = new JLabel("ID del docente: ", SwingConstants.LEFT);
		configurarEtiquetas(labelID);
		labelID.setBounds(102, 212, 150, 13);
		panel.add(labelID);
		btnVerMatriculasDocentes();
		JLabel logoP = new JLabel();
	    logoP.setVisible(false);
	    logoP.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
	    logoP.setBounds(643,290,115,130);
	    panel.add(logoP);
	    
	    JLabel logo = new JLabel(); 
	    logo.setIcon(new ImageIcon(getClass().getResource("escudo.png")));
	    logo.setBounds(643,535,115,130);
	    panel.add(logo);
	 
	    labelGrado = new JLabel("Grado: *");
	    configurarEtiquetas(labelGrado);
	    labelGrado.setBounds(102, 639, 180, 20); labelGrado.setVisible(false);
	    panel.add(labelGrado);
	    
	    lblAsignatura = new JLabel("Asignatura: *", SwingConstants.LEFT);
	    configurarEtiquetas(lblAsignatura); lblAsignatura.setVisible(false);
	    lblAsignatura.setBounds(102, 690, 180, 20);
	    panel.add(lblAsignatura);
	    
	    comboGrado = new JComboBox();
	    comboGrado.setModel(new DefaultComboBoxModel(new String[] {"  ", "Media Superior", "Licenciatura","Maestría", "Doctorado"}));
	    comboGrado.setBounds(185, 639, 145, 21);
	    comboGrado.setVisible(false);
	    panel.add(comboGrado);
	    
	    comboMateria = new JComboBox();
	    comboMateria.setModel(new DefaultComboBoxModel(new String[] {"  ", "Transformaciones","Historia de la magia", "Herbología", "Pociones", "Encantamientos", "Astronomia"}));
	    comboMateria.setBounds(225, 690, 225, 20); comboMateria.setVisible(false);
	    panel.add(comboMateria);
		elementosLabels();
		
		JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(400, 240, 130, 21);
        configurarBotones(btnActualizar);
        btnActualizar.setVisible(false);
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnActualizar.setVisible(false);
                componentesEditar(); comboGrado.setEnabled(false); comboMateria.setEnabled(false);
                actualizado=true; 
                String id = textID.getText();
                for (int i = 0; i <= indiceUltimoDocente; i++) {
                    if (listaDocente[i].getMatricula() == Integer.parseInt(id)) {
                    	listaDocente[i].setNombres(nombre.getText());
                    	listaDocente[i].setApellidos(apellido.getText());
                    	listaDocente[i].setEmail(email.getText());
                    	listaDocente[i].setTelefono(tel.getText());
                    	listaDocente[i].setFechaNacimiento(fecha.getText());
                    	listaDocente[i].setPaisNacimiento(pais.getText());
                    	listaDocente[i].setCasa((String) comboCasa.getSelectedItem());
                    	listaDocente[i].setGenero((String) comboGener.getSelectedItem());
                    	listaDocente[i].setMateria((String) comboMateria.getSelectedItem());
                    	listaDocente[i].setGrado((String) comboGrado.getSelectedItem());
                        break; 
                    }
                }
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnActualizar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(400, 240, 130, 21);
		configurarBotones(btnBuscar);
		desactivarBoton();
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
            	boolean matriculaEncontrada = false; 
            	for (int i = 0; i <= indiceUltimoDocente; i++) {
            	if (listaDocente[i].getMatricula() == Integer.parseInt(id)) {
                    nombre.setText(listaDocente[i].getNombres());
                    apellido.setText(listaDocente[i].getApellidos());
                    email.setText(listaDocente[i].getEmail());
                    tel.setText(listaDocente[i].getTelefono());
                    fecha.setText(listaDocente[i].getFechaNacimiento());
                    pais.setText(listaDocente[i].getPaisNacimiento());
                    comboCasa.setSelectedItem(listaDocente[i].getCasa());
                    comboGener.setSelectedItem(listaDocente[i].getGenero());
                    comboMateria.setSelectedItem(listaDocente[i].getMateria());
                    comboGrado.setSelectedItem(listaDocente[i].getGrado());
                    
                    componentesEditarV2(); btnActualizar.setVisible(true); logoP.setVisible(true); labelGrado.setVisible(true); lblAsignatura.setVisible(true); comboGrado.setVisible(true); comboMateria.setVisible(true);
                	String rutaFoto = listaDocente[i].getRutaFoto();
                    if (rutaFoto != null && !rutaFoto.isEmpty()) {
                        ImageIcon fotoDocente = new ImageIcon(rutaFoto);
                        logoP.setIcon(fotoDocente);
                    } else {
                    	logoPerfil.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
                    }
                    matriculaEncontrada = true;
                    break;
            	    }    
            	} if(!matriculaEncontrada) {
					JOptionPane.showMessageDialog(null, "ID de docente no encontrado","Error", JOptionPane.WARNING_MESSAGE);
				}
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnBuscar);
        btnConsNuevo = new JButton("Editar Nuevo");
        btnConsNuevo.setBounds(600, 240, 130, 21);
        configurarBotones(btnConsNuevo);
        btnConsNuevo.setVisible(false);
        btnConsNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	componentesEditarV3(); btnActualizar.setVisible(false);  logoP.setVisible(false); comboGrado.setEnabled(true);comboMateria.setEnabled(true);comboGrado.setVisible(false);comboMateria.setVisible(false);       	
            	lblAsignatura.setVisible(false); labelGrado.setVisible(false); desactivarBoton();
            	panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnConsNuevo);
		scroll();
		labelGrisDocente();
		elementosV2();
	}
	public void desactivarBoton() {
		btnBuscar.setEnabled(false);
		textID.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        if (textID.getText().isEmpty()) {
		            btnBuscar.setEnabled(false);
		        } else 
		            btnBuscar.setEnabled(true);
		    }
		});
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
	public void labelGrisDocente(){
		labelGrisV2 = new JLabel("");	labelGrisV2.setBackground(new Color(208, 205, 193));labelGrisV2.setOpaque(true);labelGrisV2.setBounds(58, 150, 750, 580);
		panel.add(labelGrisV2);
	}
	public void elementosLabels() {
		lblFecha = new JLabel("Fecha de nacimiento: ", SwingConstants.LEFT);
	    lblFecha.setVisible(false);
	    configurarEtiquetas(lblFecha);
	    lblFecha.setBounds(102, 382, 180, 20);
	    panel.add(lblFecha);
		
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
		
		lblCasa = new JLabel("Casa: ", SwingConstants.LEFT);
	    lblCasa.setVisible(false);configurarEtiquetas(lblCasa);
	    lblCasa.setBounds(350, 639, 180, 20);
	    panel.add(lblCasa);
	    
	    
	    lblTelfono = new JLabel("Teléfono: ", SwingConstants.LEFT);
		lblTelfono.setVisible(false);
		configurarEtiquetas(lblTelfono);
		lblTelfono.setBounds(102, 588, 180, 20);
		panel.add(lblTelfono);
		
		lblPais = new JLabel("País de nacimiento: ", SwingConstants.LEFT);
		configurarEtiquetas(lblPais);
	    lblPais.setVisible(false);
	    lblPais.setBounds(102, 479, 180, 20);
	    panel.add(lblPais);
	    
	    
	    lblCorreoElectr = new JLabel("Correo electrónico: ", SwingConstants.LEFT);
	    configurarEtiquetas(lblCorreoElectr);
	    lblCorreoElectr.setBounds(102, 534, 180, 20);
	    lblCorreoElectr.setVisible(false);
	    panel.add(lblCorreoElectr);
	    
	    lblGenero = new JLabel("Género: ", SwingConstants.LEFT);
	    configurarEtiquetas(lblGenero);
	    lblGenero.setVisible(false);
	    lblGenero.setBounds(102, 431, 180, 20);
	    panel.add(lblGenero);
	    
	    labelIDA = new JLabel("Nombres: ",SwingConstants.LEFT);
	    labelIDA.setVisible(false);
	    configurarEtiquetas(labelIDA);
		labelIDA.setBounds(102,291, 100, 20);
		panel.add(labelIDA);
		
		labelPS = new JLabel("Apellidos: ", SwingConstants.LEFT);
		configurarEtiquetas(labelPS);
		labelPS.setVisible(false);
		labelPS.setBounds(102, 331, 100, 20);
		panel.add(labelPS);
		
		comboCasa = new JComboBox();
		comboCasa.setModel(new DefaultComboBoxModel(new String[] {"  ", "Gryffindor","Hufflepuff", "Ravenclaw", "Slytherin\t"}));
		comboCasa.setBounds(419, 639, 159, 20);
		comboCasa.setSelectedIndex(0);
		comboCasa.setVisible(false);
	    panel.add(comboCasa);
	    
	    tel= new JTextField ("");
		tel.setVisible(false);
		tel.setFont(new Font("Tahoma", Font.BOLD, 16));
		tel.setBounds(350, 588, 228, 20);
		tel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isDigit(l)) {
                    e.consume();
                }
            }
            @Override public void keyPressed(KeyEvent e) {  }
            @Override public void keyReleased(KeyEvent e) {}
		});
		tel.setDocument(new PlainDocument() {
		    @Override
		    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		        if (getLength() + str.length() <= 10) {
		            super.insertString(offs, str, a);
		        }
		    }
		});
		panel.add(tel);
	    
		pais = new JTextField("");
	    pais.setFont(new Font("Tahoma", Font.BOLD, 16));
	    pais.setBounds(350, 479, 228, 20);
	    pais.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isLetter(l)) {
                    e.consume();
                }
            }
            @Override public void keyPressed(KeyEvent e) {  }
            @Override public void keyReleased(KeyEvent e) { }
		});
	    pais.setVisible(false);
	    panel.add(pais);
	    
	    email = new JTextField("");
	    email.setFont(new Font("Tahoma", Font.BOLD, 16));
	    email.setBounds(350, 534, 228, 20);
	    email.setVisible(false);
	    panel.add(email);
	        
	    comboGener = new JComboBox();
	    comboGener.setModel(new DefaultComboBoxModel(new String[] {"  ", "Hombre", "Mujer"}));
	    comboGener.setBounds(350, 431, 228, 21);
	    comboGener.setVisible(false);
	    panel.add(comboGener);
	   	    
		nombre = new JTextField("");
	    nombre.setFont(new Font("Tahoma", Font.BOLD, 16));
	    nombre.setVisible(false);
	    nombre.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isLetter(l) && l !=32) {
                    e.consume();
                }
            }
            @Override public void keyPressed(KeyEvent e) {  }
            @Override public void keyReleased(KeyEvent e) { }
		});
		nombre.setBounds(350, 291, 228, 20);
		panel.add(nombre);
		
		apellido = new JTextField("");
		apellido.setVisible(false);
		apellido.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isLetter(l) && l !=32) {
                    e.consume();
                }
            }
            @Override public void keyPressed(KeyEvent e) {  }
            @Override public void keyReleased(KeyEvent e) { }
		});
		apellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		apellido.setBounds(350, 331, 228, 20);
		panel.add(apellido);
	  
		fecha = new JTextField("");
		fecha.setVisible(false);
		fecha.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isDigit(l) && l !='/') {
                    e.consume();
                }
                if(textFecha.getText().length() >=10)
                	e.consume();
            }
            @Override public void keyPressed(KeyEvent e) {  }
            @Override public void keyReleased(KeyEvent e) { }
		});
		fecha.setFont(new Font("Tahoma", Font.BOLD, 16));
	    fecha.setBounds(350, 376, 228, 20);
	    panel.add(fecha);
	}
	public void elementosLabelsRegistrar() {
	    btnFoto = new JButton("Desde archivo");
	    configurarBotones(btnFoto);
	    btnFoto.setBounds(648, 363, 110, 21);
	    btnFoto.addActionListener(e -> {
            returnValue = selecFoto.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = selecFoto.getSelectedFile();
                if (selectedFile != null) {
                    logoPerfil.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
                } else {
                    JOptionPane.showMessageDialog(this, "No se seleccionó ningún archivo");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se seleccionó ningún archivo");
            }
        });   
	    panel.add(btnFoto);
	    
	    lblCasa = new JLabel("Casa: *", SwingConstants.LEFT);
	    configurarEtiquetas(lblCasa);
	    lblCasa.setBounds(350, 552, 180, 20);
	    panel.add(lblCasa);
	    
	    lblTelfono = new JLabel("Teléfono: *", SwingConstants.LEFT);
	    configurarEtiquetas(lblTelfono);
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
		textTel.setDocument(new PlainDocument() {
		    @Override
		    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		        if (getLength() + str.length() <= 10) {
		            super.insertString(offs, str, a);
		        }
		    }
		});
		panel.add(textTel);
	    
	    textPais = new JTextField();
	    textPais.setColumns(10);
	    textPais.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textPais.setBounds(350, 392, 228, 20);
	    textPais.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isLetter(l)) {
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
	    panel.add(textPais);
	    
	    lblPais = new JLabel("País de nacimiento: *", SwingConstants.LEFT);
	    configurarEtiquetas(lblPais);
	    lblPais.setBounds(102, 392, 180, 20);
	    panel.add(lblPais);
	    
	    textEmail = new JTextField();
	    textEmail.setColumns(10);
	    textEmail.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textEmail.setBounds(350, 447, 228, 20);
	    panel.add(textEmail);
	    
	    lblCorreoElectr = new JLabel("Correo electrónico: *", SwingConstants.LEFT);
	    configurarEtiquetas(lblCorreoElectr);
	    lblCorreoElectr.setBounds(102, 447, 180, 20);
	    panel.add(lblCorreoElectr);
	    
	    lblFoto = new JLabel("");
	    lblFoto.setOpaque(true);
	    lblFoto.setBackground(new Color(240, 240, 240));
	    lblFoto.setBounds(648, 223, 110, 130);
	    panel.add(lblFoto);
	    
	    comboGener = new JComboBox();
	    comboGener.setModel(new DefaultComboBoxModel(new String[] {"  ", "Hombre", "Mujer\t"}));
	    comboGener.setBounds(350, 344, 228, 21);
	    panel.add(comboGener);
	    
	    comboCasa = new JComboBox();
	    comboCasa.setModel(new DefaultComboBoxModel(new String[] {"  ", "Gryffindor","Hufflepuff", "Ravenclaw", "Slytherin\t"}));
	    comboCasa.setBounds(419, 552, 159, 20);
	    panel.add(comboCasa);
	    
	    lblGenero = new JLabel("Género: *", SwingConstants.LEFT);
	    configurarEtiquetas(lblGenero);
	    lblGenero.setBounds(102, 344, 180, 20);
	    panel.add(lblGenero);
	    
	    
	    labelID = new JLabel("Nombres: *",SwingConstants.LEFT);
	    configurarEtiquetas(labelID);
		labelID.setBounds(102,210, 100, 20);
		panel.add(labelID);
		
		labelPS = new JLabel("Apellidos: *", SwingConstants.LEFT);
		configurarEtiquetas(labelPS);
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
                if(textFecha.getText().length() >=10)
                	e.consume();
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
	    panel.add(textFecha);
	    
	    labelFecha = new JLabel("Fecha de nacimiento: *", SwingConstants.LEFT);
	    configurarEtiquetas(labelFecha);
	    labelFecha.setBounds(102, 289, 196, 20);
	    panel.add(labelFecha);
	}
	public void registrarAlumno() {
		menuVertical(); // y menu Inicio - Cerrar Sesion
		panel();
	    lblMatricula = new JLabel("Matrícula: ", SwingConstants.LEFT);
	    configurarEtiquetas(lblMatricula);
	    lblMatricula.setBounds(102, 600, 180, 20);
	    panel.add(lblMatricula);
	    
	    labelRegistro = new JLabel("Registrar alumno");
		labelRegistro.setVerticalAlignment(SwingConstants.TOP);
		labelRegistro.setForeground(new Color(0, 0, 0));
		labelRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegistro.setBounds(102, 170, 687, 30);
		panel.add(labelRegistro);
		
		lblAo = new JLabel("Año: *", SwingConstants.LEFT);
		configurarEtiquetas(lblAo);
	    lblAo.setBounds(102, 552, 180, 20);
	    panel.add(lblAo);
	    
	    comboAnio = new JComboBox();
	    comboAnio.setModel(new DefaultComboBoxModel(new String[] {"  ", "1", "2","3", "4", "5", "6", "7"}));
	    comboAnio.setBounds(171, 552, 159, 20);
	    panel.add(comboAnio);
	    
	    logoPerfil = new JLabel();
	    logoPerfil.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
	    logoPerfil.setBounds(648,220,110,130);
	    panel.add(logoPerfil);
	    
	    labelMatricula = new JLabel("");
	    labelMatricula.setFont(new Font("Tahoma", Font.BOLD, 16));
	    labelMatricula.setOpaque(false); labelMatricula.setVisible(false);
	    labelMatricula.setBounds(350, 600, 228, 20);
	    panel.add(labelMatricula);
	    
	    elementosLabelsRegistrar();
	    
	    JButton btnGuardar = new JButton("Guardar");
	    btnGuardar.setBounds(391, 650, 85, 21);
	    configurarBotones(btnGuardar);
	    btnGuardar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (textNombres.getText().length() < 3 ||
	                textApellidos.getText().length() < 3 ||
	                textTel.getText().length() == 9 ||
	                textPais.getText().length() < 3 ||
	                textEmail.getText().length() < 5 ||
	                textFecha.getText().length() == 9 ||
	                comboAnio.getSelectedItem().toString().trim().isEmpty() ||
	                comboCasa.getSelectedItem().toString().trim().isEmpty() ||
                    comboGener.getSelectedItem().toString().trim().isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Por favor faltan caracteres", "Longitud mínima no alcanzada", JOptionPane.WARNING_MESSAGE);
	                if (textNombres.getText().length() < 3) {
	                    textNombres.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textNombres.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textApellidos.getText().length() < 3) {
	                    textApellidos.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textApellidos.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textTel.getText().length() < 10) {
	                    textTel.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textTel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textFecha.getText().length() < 10) {
	                    textFecha.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                	textFecha.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textPais.getText().length() < 3) {
	                    textPais.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textPais.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textEmail.getText().length() < 5) {
	                    textEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textEmail.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (comboCasa.getSelectedItem().toString().trim().isEmpty()) {
	                    ((JComponent) comboCasa.getRenderer()).setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else comboCasa.setBorder(null);
	                if (comboGener.getSelectedItem().toString().trim().isEmpty()) {
	                    ((JComponent) comboGener.getRenderer()).setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else comboGener.setBorder(null);
	                if (comboAnio.getSelectedItem().toString().trim().isEmpty()) {
	                    ((JComponent) comboAnio.getRenderer()).setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else comboAnio.setBorder(null);
	            } else  {
	                System.out.println("Datos guardados exitosamente.");
	                matricula = rand.nextInt(1000000);
	                Alumno nuevoAlumno = new Alumno();
	                nuevoAlumno.setMatricula(matricula);
	                nuevoAlumno.setNombres(textNombres.getText());
	                nuevoAlumno.setApellidos(textApellidos.getText());
	                nuevoAlumno.setFechaNacimiento(textFecha.getText());
	                nuevoAlumno.setTelefono(textTel.getText());
	                nuevoAlumno.setPaisNacimiento(textPais.getText());
	                nuevoAlumno.setEmail(textEmail.getText());
	                nuevoAlumno.setAnio(comboAnio.getSelectedItem().toString());
	                nuevoAlumno.setCasa(comboCasa.getSelectedItem().toString());
	                nuevoAlumno.setGenero(comboGener.getSelectedItem().toString());
	                textNombres.setFocusable(false);textApellidos.setFocusable(false); textEmail.setFocusable(false); textTel.setFocusable(false);textFecha.setFocusable(false);textPais.setFocusable(false);comboCasa.setEnabled(false); comboGener.setEnabled(false); comboAnio.setEnabled(false);
	                if (returnValue == JFileChooser.APPROVE_OPTION) {
	                    File selectedFile = selecFoto.getSelectedFile();
	                    if (selectedFile != null) {
	                        logoPerfil.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
	                        nuevoAlumno.setRutaFoto(selectedFile.getAbsolutePath());
	                    } else {
	                        System.out.println("Archivo no seleccionado");
	                        logoPerfil.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
	                	    
	                    }
	                } 
	                indiceUltimoAlumno++;
	                listaAlumnos[indiceUltimoAlumno] = nuevoAlumno;
	        	    System.out.println("Matrícula creada: " + matricula);
	                labelMatricula.setVisible(true);
	                labelMatricula.setText(String.valueOf(matricula));
	                panel.revalidate();
	                panel.repaint();
	                btnGuardar.setEnabled(false);      btnRegistrarNuevo.setVisible(true);  	                btnFoto.setEnabled(false);
	            }
	        }
	    });
	    panel.add(btnGuardar);   
	    
		scroll();
		
		btnRegistrarNuevo = new JButton("Registrar Nuevo");
        btnRegistrarNuevo.setBounds(633, 650, 130, 21);
        configurarBotones(btnRegistrarNuevo);
        btnRegistrarNuevo.setVisible(false);
        btnRegistrarNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	componentesRegistrarNuevo(); btnGuardar.setEnabled(true); comboAnio.setEnabled(true);
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
	public void registrarDocente() {
		menuVertical(); // y menu Inicio - Cerrar Sesion
		panel();
	    labelRegistro = new JLabel("Registrar docente");
		labelRegistro.setVerticalAlignment(SwingConstants.TOP);
		labelRegistro.setForeground(new Color(0, 0, 0));
		labelRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegistro.setBounds(102, 170, 687, 30);
		panel.add(labelRegistro);
	    
	    logoPerfil = new JLabel();
	    logoPerfil.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
	    logoPerfil.setBounds(648,220,110,130);
	    panel.add(logoPerfil);
	    
	    lblMatricula = new JLabel("Matrícula: ", SwingConstants.LEFT);
	    configurarEtiquetas(lblMatricula);
	    lblMatricula.setBounds(102, 650, 180, 20);
	    panel.add(lblMatricula);
	    labelMatricula = new JLabel("");
	    labelMatricula.setFont(new Font("Tahoma", Font.BOLD, 16));
	    labelMatricula.setOpaque(false); labelMatricula.setVisible(false);
	    labelMatricula.setBounds(350, 650, 228, 20);
	    panel.add(labelMatricula);
	    
	    labelGrado = new JLabel("Grado: *");
	    configurarEtiquetas(labelGrado);
	    labelGrado.setBounds(102, 552, 180, 20);
	    panel.add(labelGrado);
	    
	    lblAsignatura = new JLabel("Asignatura: *", SwingConstants.LEFT);
	    configurarEtiquetas(lblAsignatura);
	    lblAsignatura.setBounds(102, 600, 180, 20);
	    panel.add(lblAsignatura);
	    
	    comboGrado = new JComboBox();
	    comboGrado.setModel(new DefaultComboBoxModel(new String[] {"  ", "Media Superior", "Licenciatura\t","Maestría\t", "Doctorado\t"}));
	    comboGrado.setBounds(185, 552, 145, 21);
	    panel.add(comboGrado);
	    
	    comboMateria = new JComboBox();
	    comboMateria.setModel(new DefaultComboBoxModel(new String[] {"  ", "Transformaciones","Historia de la magia", "Herbología", "Pociones\t", "Encantamientos", "Astronomia"}));
	    comboMateria.setBounds(225, 600, 225, 20);
	    panel.add(comboMateria);
	    
	    elementosLabelsRegistrar();
		JButton btnGuardar = new JButton("Guardar");
	    btnGuardar.setBounds(391, 690, 85, 21);
	    configurarBotones(btnGuardar);
	    btnGuardar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (textNombres.getText().length() < 3 ||
	                textApellidos.getText().length() < 3 ||
	                textTel.getText().length() == 9 ||
	                textPais.getText().length() < 3 ||
	                textEmail.getText().length() < 5 ||
	                textFecha.getText().length() == 9 ||
	                comboCasa.getSelectedItem().toString().trim().isEmpty() ||
                    comboGener.getSelectedItem().toString().trim().isEmpty() ||
                    comboGrado.getSelectedItem().toString().trim().isEmpty() ||
                    comboMateria.getSelectedItem().toString().trim().isEmpty()  ) {
	                // Muestra un mensaje de advertencia
	                JOptionPane.showMessageDialog(null, "Por favor faltan caracteres", "Longitud mínima no alcanzada", JOptionPane.WARNING_MESSAGE);

	                // Resalta los campos que no cumplen con el requisito
	                if (textNombres.getText().length() < 3) {
	                    textNombres.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textNombres.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textApellidos.getText().length() < 3) {
	                    textApellidos.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textApellidos.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textTel.getText().length() == 10) {
	                    textTel.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textTel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textFecha.getText().length() == 10) {
	                    textFecha.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                	textFecha.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textPais.getText().length() < 3) {
	                    textPais.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textPais.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (textEmail.getText().length() < 5) {
	                    textEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else {
	                    textEmail.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                }
	                if (comboCasa.getSelectedItem().toString().trim().isEmpty()) {
	                    ((JComponent) comboCasa.getRenderer()).setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else comboCasa.setBorder(null);
	                if (comboGener.getSelectedItem().toString().trim().isEmpty()) {
	                    ((JComponent) comboGener.getRenderer()).setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else comboGener.setBorder(null);
	                if (comboGrado.getSelectedItem().toString().trim().isEmpty()) {
	                    ((JComponent) comboGrado.getRenderer()).setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else comboGrado.setBorder(null);
	                if (comboMateria.getSelectedItem().toString().trim().isEmpty()) {
	                    ((JComponent) comboMateria.getRenderer()).setBorder(BorderFactory.createLineBorder(Color.RED));
	                } else comboMateria.setBorder(null);

	            } else {
	                System.out.println("Datos guardados exitosamente Docente.");
	                matricula = rand.nextInt(1000000);
	                Docente nuevoDocente = new Docente();
	                nuevoDocente.setMatricula(matricula);
	                nuevoDocente.setNombres(textNombres.getText());
	                nuevoDocente.setApellidos(textApellidos.getText());
	                nuevoDocente.setFechaNacimiento(textFecha.getText());
	                nuevoDocente.setTelefono(textTel.getText());
	                nuevoDocente.setPaisNacimiento(textPais.getText());
	                nuevoDocente.setEmail(textEmail.getText());
	                nuevoDocente.setCasa(comboCasa.getSelectedItem().toString());
	                nuevoDocente.setGenero(comboGener.getSelectedItem().toString());
	                nuevoDocente.setGrado(comboGrado.getSelectedItem().toString());
	                nuevoDocente.setMateria(comboMateria.getSelectedItem().toString());
	                textNombres.setFocusable(false);textApellidos.setFocusable(false); textEmail.setFocusable(false); textTel.setFocusable(false);textFecha.setFocusable(false);textPais.setFocusable(false);comboCasa.setEnabled(false); comboGrado.setEnabled(false);comboMateria.setEnabled(false);comboGener.setEnabled(false); 
	                if (returnValue == JFileChooser.APPROVE_OPTION) {
	                    File selectedFile = selecFoto.getSelectedFile();
	                    if (selectedFile != null) {
	                        logoPerfil.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
	                        nuevoDocente.setRutaFoto(selectedFile.getAbsolutePath());
	                    } else {
	                        System.out.println("Archivo no seleccionado");
	                        logoPerfil.setIcon(new ImageIcon(getClass().getResource("perfil.png")));
	                	    
	                    }
	                } 
	                indiceUltimoDocente++;
	                listaDocente[indiceUltimoDocente] = nuevoDocente;
	        	    System.out.println("Matrícula creada: " + matricula);
	                labelMatricula.setVisible(true);
	                labelMatricula.setText(String.valueOf(matricula));
	                panel.revalidate();
	                panel.repaint();
	                btnGuardar.setEnabled(false);      btnRegistrarNuevo.setVisible(true);  	                btnFoto.setEnabled(false);
	        }
	       }
	    });
	    panel.add(btnGuardar);
	    
	    scroll();
		btnRegistrarNuevo = new JButton("Registrar Nuevo");
        btnRegistrarNuevo.setBounds(633, 690, 130, 21);
        configurarBotones(btnRegistrarNuevo);
        btnRegistrarNuevo.setVisible(false);
        btnRegistrarNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	componentesRegistrarNuevo();btnGuardar.setEnabled(true); comboMateria.setEnabled(true);comboGrado.setEnabled(true); 
            	panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(btnRegistrarNuevo);
		elementosV2();
		labelGrisDocente();
	}
	public void componentesRegistrarNuevo() {
		textNombres.setText("");  textApellidos.setText("");  textFecha.setText("DD/MM/AAAA"); textFecha.setForeground(Color.GRAY);
        textTel.setText(""); textPais.setText(""); textEmail.setText(""); comboCasa.setSelectedIndex(0); comboGener.setSelectedIndex(0); labelMatricula.setText(""); btnFoto.setEnabled(true);
        textNombres.setFocusable(true);textApellidos.setFocusable(true); textEmail.setFocusable(true);  textTel.setFocusable(true);textFecha.setFocusable(true);
        textPais.setFocusable(true);comboCasa.setEnabled(true); comboGener.setEnabled(true); btnRegistrarNuevo.setVisible(false); 
	}
	public void crearPersonas() {
		Alumno a1 = new Alumno (303030,"Harry James", "Potter", "30/07/1980", "7771237770",  "Inglaterra", "hp7@wizardingWorld.com", "6", "Gryffindor", "Hombre", "src/Harry.jpg");
		indiceUltimoAlumno++; listaAlumnos[indiceUltimoAlumno] = a1;
		Alumno a2 = new Alumno (191919,"Hermione Jean", "Granger", "19/09/1917", "1907191700",  "Inglaterra", "granger19@wizardingWorld.com", "6", "Gryffindor", "Mujer", "src/Hermione.jpg");
		indiceUltimoAlumno++; listaAlumnos[indiceUltimoAlumno] = a2;
		Alumno a3 = new Alumno (198080,"Ronald Bilius", "Weasley", "01/03/1980", "0103198008",  "Inglaterra", "weasley6@wizardingWorld.com", "6", "Gryffindor", "Hombre", "src/Ron.jpg");
		indiceUltimoAlumno++; listaAlumnos[indiceUltimoAlumno] = a3;
		Docente d1 = new Docente(191010, "Minerva", "McGonagall", "04/10/1910", "0110191010", "Escocia", "mg4@wizardingWorld.com", "Gryffindor", "Mujer", "Maestría",  "Transformaciones", "src/Minerva.jpg");
		indiceUltimoDocente++; listaDocente[indiceUltimoDocente] = d1;
		Docente d2 = new Docente(196001, "Severus", "Snape", "09/01/1960", "0901196060", "Inglaterra", "snape09@wizardingWorld.com", "Slytherin", "Hombre","Licenciatura",  "Pociones",  "Snape.jpg");
		indiceUltimoDocente++; listaDocente[indiceUltimoDocente] = d2;
		Docente d3 = new Docente(171717, "Filius", "Flitwick", "17/10/1958", "1710195858", "Irlanda", "flitwick17@wizardingWorld.com", "Ravenclaw", "Hombre",  "Doctorado", "Encantamientos","src/filius.jpg");
		indiceUltimoDocente++; listaDocente[indiceUltimoDocente] = d3;
	}
}