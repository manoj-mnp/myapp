package com.abhimantech.hiree.hireelocal.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Collection;

import javax.swing.*;

import com.abhimantech.hiree.hireelocal.FileListParser;
import com.abhimantech.hiree.hireelocal.callbacks.FileListFetcherCallback;

public class FileExplorer implements FileListFetcherCallback {

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public FileExplorer() {
		prepareGUI();
	}

	public static void main(String[] args) {
		FileExplorer fileExplorerDemo = new FileExplorer();
		fileExplorerDemo.showFileChooserDemo();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("File Selector");

		// make the frame half the height and width
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		mainFrame.setSize(width / 2, height / 2);
		mainFrame.setLocationRelativeTo(null);

		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);

		statusLabel.setSize(350, 100);

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void showFileChooserDemo() {
		headerLabel.setText("Please select a folder: ");

		final JFileChooser fileDialog = new JFileChooser();
		fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileDialog.setAcceptAllFileFilterUsed(false);
		JButton showFileDialogButton = new JButton("Open File");
		showFileDialogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialog.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					java.io.File file = fileDialog.getSelectedFile();
					//statusLabel.setText("File Selected: " + file.getName());
					Thread t = new Thread(new FileListParser(FileExplorer.this, file.getAbsolutePath()));
					t.start();
				} else {
					statusLabel.setText("File selection operation cancelled by user.");
				}
			}
		});
		controlPanel.add(showFileDialogButton);
		mainFrame.setVisible(true);
	}

	public void callback(Collection<File> fileList) {
		statusLabel.setText(fileList.size()+" files found");
	}
}
