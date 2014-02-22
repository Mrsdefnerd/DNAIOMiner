package org.parabot.mrsdefnerd.aiominer.core;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.parabot.core.ui.components.LogArea;
import org.parabot.mrsdefnerd.aiominer.constants.MiningType;
import org.parabot.mrsdefnerd.aiominer.constants.Rocks;
import org.parabot.mrsdefnerd.aiominer.data.Data;

public class UI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6913398644437885641L;
	
	public boolean isRunning = true;

	public UI() {
		init();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
		setLayout(null);
		setSize(355, 340);
		setTitle("DNAIOMiner");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		JLabel rockLabel = new JLabel("Select a rock to mine");
		rockLabel.setBounds(10, 10, rockLabel.getPreferredSize().width, 20);
		add(rockLabel);
		
		JLabel mineLabel = new JLabel("Select a banking type");
		mineLabel.setBounds(180, 10, mineLabel.getPreferredSize().width, 20);
		add(mineLabel);
		
		DefaultListModel rockModel = new DefaultListModel();
		final JList rockList = new JList(rockModel);
		JScrollPane pane = new JScrollPane(rockList);
		pane.setBounds(10, 30, 150, 200);
		for (Rocks rock : Rocks.values())
			rockModel.addElement(rock.toString());
		add(pane);
		
		final DefaultListModel mineModel = new DefaultListModel();
		final JList mineList = new JList(mineModel);
		JScrollPane minePane = new JScrollPane(mineList);
		minePane.setBounds(180, 30, 150, 200);
		add(minePane);
		setVisible(true);
		
		rockList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				mineModel.clear();
				for (MiningType type : Rocks.values()[rockList.getSelectedIndex()].getTypes())
					mineModel.addElement(type.toString());
			}
			
		});
		
		JButton start = new JButton("Start");
		start.setFont(new Font("Verdana", Font.BOLD, 15));
		start.setBounds(10, 250, 320, 40);
		add(start);
		
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (mineList.isSelectionEmpty() || rockList.isSelectionEmpty()) {
					LogArea.error("Please select "+ (mineList.isSelectionEmpty() ? "a rock to mine." : "a mining type."));
					return;
				}
				Data.rocks = Rocks.values()[rockList.getSelectedIndex()];
				Data.type = Rocks.values()[rockList.getSelectedIndex()].getTypes()[mineList.getSelectedIndex()];
				setVisible(false);
				isRunning = false;
			}
			
		});
		
	}
	
	public static void main(String...args) {
		new UI();
	}

}
