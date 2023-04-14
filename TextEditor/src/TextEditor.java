import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of text editor
    JFrame frame;

    JMenuBar menuBar;
    JMenu file,edit;

    //file menu item
    JMenuItem newFile,openFile,saveFile;

    //edit menu items
    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;


    TextEditor() {   //constructor
        frame =new JFrame();  //initializing a newFrame

        menuBar=new JMenuBar();  // initializing menuBar

        textArea=new JTextArea();  //initializing textArea

        //Initialize menu
        file=new JMenu("file");
        edit=new JMenu("edit");

        //Initialize file menuItems
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        //Add action listener to file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menu item to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initializing edit menuItems
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        //adding actionListener to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        //Adding menus to menubar
        menuBar.add(file);
        menuBar.add(edit);


        //set menuBar to frame
        frame.setJMenuBar(menuBar);

       //Create content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //Create scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

        //setting frame dimensions
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor by Alok");

        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){

        if(actionEvent.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }

        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }

        if(actionEvent.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }

        if(actionEvent.getSource()==selectAll){
            //perform cut operation
            textArea.selectAll();
        }

        if(actionEvent.getSource()==close){
            //perform close operation
            System.exit(0);
        }

        if(actionEvent.getSource()==openFile){
            //open a file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file=fileChooser.getSelectedFile();
                //Get filePath of selected file
                String filePath=file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader=new FileReader(filePath);
                    //Initialize Buffered Reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="", output="";
                    //Read contents of file line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==saveFile){
            //Initialize file picker
            JFileChooser fileChooser=new JFileChooser("C:");
            //Get choose option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);
            //Check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Create a new file with chosen directory path and file name
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter=new FileWriter(file);
                    //Initialize buffered reader
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    //Write contents of text area file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor=new TextEditor();
        }

    }

    public static void main(String[] args) {

        TextEditor texteditor=new TextEditor(); //instance for text editor class
    }
}