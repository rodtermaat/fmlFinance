/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fmlUi;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.sql.Date;
import java.util.Calendar;

//used for timer
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Calendar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

//does the sorting of the arraylist
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author termaat
 */
public class fmlUi extends javax.swing.JFrame {

    int lstInsertID = 0;
    int row_num = 0;
    int rec_id = 0;
    int monTracker = 0;
    int weekTracker = 8;

     ArrayList<Transaction> ledgerList = new ArrayList<Transaction>();
            // ArrayList of the Transaction object
            // the ledger/checkbook of the application
    
     //Transaction row;
            // not sure what this actually does, 
    
    SQLite sqlite = new SQLite();
            // created a new object to do db interaction
                    
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
    
    // date used globally to test against current date - should never change
    static java.util.Date today = new java.util.Date();
    java.sql.Date sqlToday = new java.sql.Date(today.getTime());
    String stringToday = sdf.format(sqlToday.getTime());
    
    // this it the sql future date used to get the 
    // forecasted balance
    String futureDate = "2525-01-01"; // YYYY-MM-DD
    String futureDatex = "01/01/2525";
    java.sql.Date sqlFutureDate = java.sql.Date.valueOf(futureDate);
    
    // sqlDate used to pass to various function of sqlite class
    // always changing based in user input
    java.sql.Date sqlDate = new java.sql.Date(today.getTime());
    
    FreakyDate Freaky = new FreakyDate();
    
    DefaultTableModel model;
    
    
    /**
     * Creates new form fmlUi
     */
    public fmlUi() {
        initComponents();
        
        // application title
        this.setTitle("Welcome to fml Finance");
        
        // variable text around table border
        jPanel1.setBorder(BorderFactory.createTitledBorder(Freaky.getMonthDesc(0)));
        
        // underline a label
        Font font = lblBudget.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        lblBudget.setFont(font.deriveFont(attributes));
        
        //set up model for tblLedger
        model = (DefaultTableModel) tblLedger.getModel();
        
        //Set starting date ranges
        java.sql.Date fomDate = Freaky.getActualFOM(0);
        java.sql.Date eomDate = Freaky.getEOM(0);
        java.sql.Date fomSQL = Freaky.getFOM(-1);
        
        //fill ledger and set radio buttons
        ListTransactionsNew(fomSQL, eomDate);
        RefreshAnalytics(fomDate, fomSQL, eomDate);
        DisplayCatSummary(fomSQL, eomDate);
        
        rdoByMonth.setSelected(true);
        rdoShowAll.setSelected(false);
        rdoByWeek.setSelected(false);
        
        //Analytics date range
        //lblFirstDay.setText(String.valueOf(fomDate));
        //lblLastDay.setText(String.valueOf(eomDate));
        
        //Analytics balances
        //int row = (tblLedger.getRowCount() -1);
        //if(row>0){
        //lblEndingBal.setText(String.valueOf(model.getValueAt(row,5)));
        //lblStartingBal.setText(String.valueOf(model.getValueAt(0,5)));
        //}
        
        //Analytics summary
        //int groceries = 0; int dining = 0; int gas = 0; int unplanned = 0;
        //groceries = sqlite.getCategorySum(fomSQL, eomDate, "Groceries");
        //dining = sqlite.getCategorySum(fomSQL, eomDate, "Dining");
        //gas = sqlite.getCategorySum(fomSQL, eomDate, "Auto");
        //unplanned = sqlite.getCategorySum(fomSQL, eomDate, "Unplanned");
        //lblGroceries.setText(String.valueOf(groceries));
        //lblDining.setText(String.valueOf(dining));
        //lblGas.setText(String.valueOf(gas));
        //lblUnplanned.setText(String.valueOf(unplanned));
        
        
        // see of the db is already set up.  0 means there is no table
        int isReady = sqlite.IsAcctSetup();
        if(isReady == 0){
            sqlite.createDB();
            sqlite.createTranTbl();
            displayMessage("Enter 1st transaction to set up Acct. balance");
        }   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblCurrentBal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLedger = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        rdoShowAll = new javax.swing.JRadioButton();
        rdoByMonth = new javax.swing.JRadioButton();
        rdoByWeek = new javax.swing.JRadioButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblStartingBal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblEndingBal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        txtFormatDate = new javax.swing.JFormattedTextField();
        cbRepeater = new javax.swing.JCheckBox();
        rdoDelete = new javax.swing.JRadioButton();
        rdoUpdate = new javax.swing.JRadioButton();
        rdoClear = new javax.swing.JRadioButton();
        btnDoIt = new javax.swing.JButton();
        rdoDeposit = new javax.swing.JRadioButton();
        rdoExpense = new javax.swing.JRadioButton();
        lblDebug = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        JPanelPie = new javax.swing.JPanel();
        lblGroceries = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDining = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblGas = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblUnplanned = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblBudget = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCash = new javax.swing.JLabel();
        lblFirstDay = new javax.swing.JLabel();
        lblLastDay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ledger", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        jLabel1.setText("Today");

        lblCurrentBal.setBackground(new java.awt.Color(255, 204, 0));
        lblCurrentBal.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblCurrentBal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentBal.setText("$1250");
        lblCurrentBal.setOpaque(true);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 200));
        jScrollPane2.setSize(new java.awt.Dimension(300, 200));

        tblLedger.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        tblLedger.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "date", "category", "name", "amount", "balance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLedger.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblLedger.setShowHorizontalLines(false);
        tblLedger.getTableHeader().setReorderingAllowed(false);
        tblLedger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLedgerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLedger);
        if (tblLedger.getColumnModel().getColumnCount() > 0) {
            tblLedger.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblLedger.getColumnModel().getColumn(1).setMinWidth(75);
            tblLedger.getColumnModel().getColumn(1).setPreferredWidth(75);
            tblLedger.getColumnModel().getColumn(1).setMaxWidth(75);
            tblLedger.getColumnModel().getColumn(2).setMinWidth(80);
            tblLedger.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblLedger.getColumnModel().getColumn(2).setMaxWidth(80);
            tblLedger.getColumnModel().getColumn(3).setMinWidth(95);
            tblLedger.getColumnModel().getColumn(3).setPreferredWidth(95);
            tblLedger.getColumnModel().getColumn(3).setMaxWidth(95);
            tblLedger.getColumnModel().getColumn(4).setMinWidth(65);
            tblLedger.getColumnModel().getColumn(4).setPreferredWidth(65);
            tblLedger.getColumnModel().getColumn(4).setMaxWidth(65);
            tblLedger.getColumnModel().getColumn(5).setMinWidth(70);
            tblLedger.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblLedger.getColumnModel().getColumn(5).setMaxWidth(70);
        }

        rdoShowAll.setLabel("show all");
        rdoShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoShowAllActionPerformed(evt);
            }
        });

        rdoByMonth.setText("by month");
        rdoByMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoByMonthActionPerformed(evt);
            }
        });

        rdoByWeek.setLabel("by week");
        rdoByWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoByWeekActionPerformed(evt);
            }
        });

        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.setPreferredSize(new java.awt.Dimension(40, 29));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblMessage.setText("Welcome to fml Finance");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoShowAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoByMonth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoByWeek)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoShowAll)
                    .addComponent(rdoByMonth)
                    .addComponent(rdoByWeek)
                    .addComponent(btnPrev)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMessage)))
        );

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        jLabel4.setText("Starting Balance on...");

        lblStartingBal.setBackground(new java.awt.Color(153, 255, 255));
        lblStartingBal.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        lblStartingBal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStartingBal.setText("15000");
        lblStartingBal.setOpaque(true);

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        jLabel11.setText("Ending Balance on...");

        lblEndingBal.setBackground(new java.awt.Color(153, 255, 255));
        lblEndingBal.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        lblEndingBal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEndingBal.setText("32000");
        lblEndingBal.setOpaque(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transaction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N

        jLabel5.setText("Amount");

        txtAmount.setToolTipText("Enter the Amount of the transaction");
        txtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountActionPerformed(evt);
            }
        });
        txtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAmountKeyTyped(evt);
            }
        });

        jLabel6.setText("Desc");

        txtName.setToolTipText("Enter a name for the item");
        txtName.setMinimumSize(new java.awt.Dimension(150, 26));

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Deposit", "Housing", "Living", "Debt", "Savings", "Cash", "Auto", "Groceries", "Dining", "Unplanned", "Salary", "Transfer", "Open", "Adjustment" }));
        cmbCategory.setToolTipText("");
        cmbCategory.setMinimumSize(new java.awt.Dimension(125, 27));
        cmbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoryActionPerformed(evt);
            }
        });

        jLabel7.setText("Category");

        jLabel8.setText("Date");

        btnAdd.setText("Add");
        btnAdd.setToolTipText("");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        txtFormatDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("MM/dd/yy"))));
        txtFormatDate.setMinimumSize(new java.awt.Dimension(40, 26));

        cbRepeater.setText("Repeat?");
        cbRepeater.setToolTipText("enable repeat entries");

        rdoDelete.setText("delete");
        rdoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDeleteActionPerformed(evt);
            }
        });

        rdoUpdate.setText("update");
        rdoUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoUpdateActionPerformed(evt);
            }
        });

        rdoClear.setText("clear");
        rdoClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoClearActionPerformed(evt);
            }
        });

        btnDoIt.setText("Do it");
        btnDoIt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoItActionPerformed(evt);
            }
        });

        rdoDeposit.setText("deposit");
        rdoDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDepositActionPerformed(evt);
            }
        });

        rdoExpense.setText("expense");
        rdoExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoExpenseActionPerformed(evt);
            }
        });

        lblDebug.setText("debug label");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDeposit))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoUpdate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoExpense)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDebug, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDoIt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbRepeater)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd)
                        .addGap(47, 47, 47))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFormatDate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoDeposit)
                    .addComponent(rdoExpense)
                    .addComponent(lblDebug))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtFormatDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDelete)
                    .addComponent(rdoUpdate)
                    .addComponent(rdoClear)
                    .addComponent(btnDoIt)
                    .addComponent(cbRepeater)
                    .addComponent(btnAdd))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analytics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N

        javax.swing.GroupLayout JPanelPieLayout = new javax.swing.GroupLayout(JPanelPie);
        JPanelPie.setLayout(JPanelPieLayout);
        JPanelPieLayout.setHorizontalGroup(
            JPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        JPanelPieLayout.setVerticalGroup(
            JPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblGroceries.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGroceries.setText("0000");

        jLabel3.setText("Groceries");

        lblDining.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDining.setText("0000");

        jLabel10.setText("Dining");

        lblGas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGas.setText("0000");

        jLabel13.setText("Auto");

        lblUnplanned.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUnplanned.setText("0000");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Unplanned");

        lblBudget.setText("Budget Tracking");

        jLabel2.setText("Cash");

        lblCash.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCash.setText("0000");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBudget)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblUnplanned, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGroceries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(57, 57, 57)
                .addComponent(JPanelPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblBudget)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCash, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblGroceries))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblUnplanned)))
            .addComponent(JPanelPie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblFirstDay.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        lblFirstDay.setText("2018-10-31");
        lblFirstDay.setToolTipText("");

        lblLastDay.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        lblLastDay.setText("2018-11-30");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFirstDay)
                                .addGap(18, 18, 18)
                                .addComponent(lblStartingBal, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCurrentBal, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblLastDay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEndingBal, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(lblStartingBal)
                            .addComponent(lblFirstDay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCurrentBal)
                            .addComponent(jLabel11)
                            .addComponent(lblLastDay)
                            .addComponent(lblEndingBal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 996, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
         
        //validate the data entry
        String validDE = validateDE();
        
        //if data entry passes then make the update
        if(validDE.equals("FAIL")){
            displayMessage("Data Entry failure. Please validate entry.");
        }
        else{
          // if data entry passed then we will add the transaction
          addTransaction();
          if(rdoShowAll.isSelected()){
            ListTransactions();
            rdoShowAll.setSelected(true);
            rdoByMonth.setSelected(false);
            rdoByWeek.setSelected(false);
          }
          if (rdoByMonth.isSelected()){
              ListTransactions();
              // TO DO
              // Make this retain month view somehow
          }
        }
          
        //check if the repeat is turned on
        if(!cbRepeater.isSelected()){
          clearDE();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyTyped
        // TODO add your handling code here:
        char vchar = evt.getKeyChar();
        if(!Character.isDigit(vchar)
            || (vchar == KeyEvent.VK_BACKSPACE)
            || (vchar == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_txtAmountKeyTyped

    private void tblLedgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLedgerMouseClicked
        //java.util.Date date;
        //String sdate = "";
        
        int i = tblLedger.getSelectedRow();
        row_num = tblLedger.getSelectedRow();
        
        String ids = String.valueOf(model.getValueAt(i, 0));
        int id = Integer.valueOf(ids);
        rec_id = Integer.valueOf(ids);
        
        lblDebug.setText("row: " + String.valueOf(row_num) + 
                " record id: " + rec_id);
        
        String s_amt = String.valueOf(model.getValueAt(i,4));
        int amt = Integer.valueOf(s_amt);
        //if(model.getValueAt(i,4).equals("Deposit")){
        if(amt < 0){
            amt = amt * -1;
            rdoExpense.setSelected(true);
        }
        else{
            rdoDeposit.setSelected(true);
        }
        
        txtAmount.setText(String.valueOf(amt));
        //txtAmount.setText(String.valueOf(model.getValueAt(i, 4)));
        
        txtName.setText(String.valueOf(model.getValueAt(i, 3)));
        
        cmbCategory.setSelectedItem(model.getValueAt(i, 2));
        //cmbCategory.setText(String.valueOf(model.getValueAt(i, 3)));
        
        String ymddate = String.valueOf(model.getValueAt(i, 1));
        String mdydate = FormatDate(ymddate);
        txtFormatDate.setText(mdydate);
    }//GEN-LAST:event_tblLedgerMouseClicked

    private void rdoClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoClearActionPerformed
        if(rdoClear.isSelected()){
            rdoDelete.setSelected(false);
            rdoUpdate.setSelected(false);
        }
    }//GEN-LAST:event_rdoClearActionPerformed
    private void clearDE(){
        txtAmount.setText("");
        txtName.setText("");
        txtFormatDate.setText("");
        cmbCategory.setSelectedIndex(-1);
        rdoClear.setSelected(false);
        rdoDelete.setSelected(false);
        rdoUpdate.setSelected(false);
        rdoDeposit.setSelected(false);
        rdoExpense.setSelected(false);
    }
    
    // takes the date from database and reformats it to something
    // easier to data entry
    private String FormatDate(String ymddate){
        java.util.Date date;
        String mdydate = "";
        
        SimpleDateFormat mdyFormattedDate = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat ymdFormattedDate = new SimpleDateFormat("yyyy-MM-dd");
       
        try
        {
          date = ymdFormattedDate.parse(ymddate);
          mdydate = mdyFormattedDate.format(date);          
        }
          catch(ParseException e) {
          displayMessage("Enter date like 04/15/18 " +
                        "and not some other bogus format" +
                        " and not: " + ymddate);
          clearDE();
        }

        return mdydate;
    }
    
    
    private void btnDoItActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoItActionPerformed
        java.util.Date date;
        int amt = 0;
        int new_amt = 0;
        
        if(rdoClear.isSelected()){
          clearDE();
        }
        if(rdoUpdate.isSelected()){
    
          int amount = Integer.parseInt(txtAmount.getText());
          String name = txtName.getText();
          String category = (String)cmbCategory.getSelectedItem();
          String idate = txtFormatDate.getText();
        
          if (rdoDeposit.isSelected()){}
          else if (rdoExpense.isSelected()){
            amount = amount * -1;}
          else {
              displayMessage("Select Deposit or Expense");
          }
        
          SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yy");
        
          try
          {
            date = formatDate.parse(idate);
            sqlDate = new java.sql.Date(date.getTime());
          }
          catch(ParseException e) {
            displayMessage("addTransaction: Enter date like 04/15/18");
            return;
          }     
           
          String ids = String.valueOf(model.getValueAt(row_num, 0));
          int id = Integer.valueOf(ids);
                
            //update the transaction to db
            sqlite.UpdateTran(id, sqlDate, category, name, amount);
            ListTransactions();
            rdoShowAll.setSelected(true);
            rdoByMonth.setSelected(false);
            rdoByWeek.setSelected(false);
            clearDE();
        }
        if (rdoDelete.isSelected()){
          
            //validate the data entry
            String validDE = validateDE();
        
            //if data entry passes then make the update
            if(validDE.equals("FAIL")){
                displayMessage("Data Entry failure. Please validate entry.");
            }
            else{
                //validateDE();
          
                // deal with deposit/expense sign
                if(rdoDeposit.isSelected()){
                    String s_amt = String.valueOf(txtAmount.getText());
                    amt = Integer.valueOf(s_amt);
                    amt = amt * -1;
                }
                else{
                    String s_amt = String.valueOf(txtAmount.getText());
                    amt = Integer.valueOf(s_amt);
                }
        
                
                // do the delete
                // here we need the id to delete
                int i = tblLedger.getSelectedRow();

                String ids = String.valueOf(model.getValueAt(i, 0));
                int id = Integer.valueOf(ids);
            
                sqlite.deleteTran(id);
        
                // if everything went fine
                //displayMessage("Delete selected");
                clearDE();
                ListTransactions();
                rdoShowAll.setSelected(true);
                rdoByMonth.setSelected(false);
                rdoByWeek.setSelected(false);
            }
        }
    
    }//GEN-LAST:event_btnDoItActionPerformed

    private void rdoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDeleteActionPerformed
           if(rdoDelete.isSelected()){
            rdoClear.setSelected(false);
            rdoUpdate.setSelected(false);
        }
    }//GEN-LAST:event_rdoDeleteActionPerformed

    private void rdoUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoUpdateActionPerformed
        if(rdoUpdate.isSelected()){
            rdoClear.setSelected(false);
            rdoDelete.setSelected(false);
        }
    }//GEN-LAST:event_rdoUpdateActionPerformed

    private void rdoExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoExpenseActionPerformed
        if(rdoExpense.isShowing()){
            rdoDeposit.setSelected(false);
        }
    }//GEN-LAST:event_rdoExpenseActionPerformed

    private void rdoDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDepositActionPerformed
        if(rdoDeposit.isSelected()){
            rdoExpense.setSelected(false);
        }
    }//GEN-LAST:event_rdoDepositActionPerformed

    // the default selection for ListTransactions whereby everything is shown
    private void rdoShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoShowAllActionPerformed
        rdoByMonth.setSelected(false);
        rdoByWeek.setSelected(false);
        
        this.ListTransactions();;
        
    }//GEN-LAST:event_rdoShowAllActionPerformed
    // show Transactions for the current month only
    private void rdoByMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoByMonthActionPerformed
        rdoShowAll.setSelected(false);
        rdoByWeek.setSelected(false);
        
        java.sql.Date eomDate = Freaky.getEOM(0);
        java.sql.Date fomSQL = Freaky.getFOM(-1);
        java.sql.Date fomDate = Freaky.getActualFOM(0);
        jPanel1.setBorder(BorderFactory.createTitledBorder(Freaky.getMonthDesc(0)));
        
        //lblFirstDay.setText(String.valueOf(fomDate));
        //lblLastDay.setText(String.valueOf(eomDate));
        
        ListTransactionsNew(fomSQL, eomDate);
        RefreshAnalytics(fomDate, fomSQL, eomDate);
        DisplayCatSummary(fomSQL, eomDate);
        
        //int row = (tblLedger.getRowCount() -1);
        //if(row>0){
        //lblEndingBal.setText(String.valueOf(model.getValueAt(row,5)));
        //lblStartingBal.setText(String.valueOf(model.getValueAt(0,5)));
        //}
    }//GEN-LAST:event_rdoByMonthActionPerformed
    // show Transactions for the current week
    private void rdoByWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoByWeekActionPerformed
        rdoShowAll.setSelected(false); rdoByMonth.setSelected(false);
        
        java.sql.Date eowDate = Freaky.getEOW(8);
        java.sql.Date fowDate = Freaky.getFOW(8);
         
        lblFirstDay.setText(String.valueOf(fowDate));
        lblLastDay.setText(String.valueOf(eowDate));
        
        ListTransactionsNew(fowDate, eowDate);
        
        
        int row = (tblLedger.getRowCount() -1);
        if(row>0){
        lblEndingBal.setText("$ " + String.valueOf(model.getValueAt(row,5)));
        lblStartingBal.setText("$ " + String.valueOf(model.getValueAt(0,5)));
        }
    }//GEN-LAST:event_rdoByWeekActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        
        //move forward a month in transaction list
        if(rdoByMonth.isSelected()){
        
            monTracker++;
            
            java.sql.Date eomDate = Freaky.getEOM(monTracker);
            java.sql.Date fomSQL = Freaky.getFOM(monTracker-1);
            java.sql.Date fomDate = Freaky.getActualFOM(monTracker);
            jPanel1.setBorder(BorderFactory.createTitledBorder(Freaky.getMonthDesc(monTracker)));
            
            //lblFirstDay.setText(String.valueOf(fomDate));
            //lblLastDay.setText(String.valueOf(eomDate));
        
            ListTransactionsNew(fomSQL, eomDate);   
            RefreshAnalytics(fomDate, fomSQL, eomDate);
            DisplayCatSummary(fomSQL, eomDate);
            
            //int row = (tblLedger.getRowCount() -1);
            //if(row>0){
            //lblEndingBal.setText(String.valueOf(model.getValueAt(row,5)));
            //lblStartingBal.setText(String.valueOf(model.getValueAt(0,5)));
            //}
        }
        if (rdoByWeek.isSelected()){
            
            weekTracker = weekTracker+7;
            java.sql.Date eowDate = Freaky.getEOW(weekTracker-1);
            java.sql.Date fowDate = Freaky.getFOW(weekTracker);
            
            lblFirstDay.setText(String.valueOf(fowDate));
            lblLastDay.setText(String.valueOf(eowDate));
            
            ListTransactionsNew(fowDate, eowDate);
            
            
            int row = (tblLedger.getRowCount() -1);
            if(row>0){
            lblEndingBal.setText("$ " + String.valueOf(model.getValueAt(row,5)));
            lblStartingBal.setText("$ " + String.valueOf(model.getValueAt(0,5)));
            }
        }
        
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        
        
        //move forward a month in transaction list
        if(rdoByMonth.isSelected()){
            monTracker--;
        
            java.sql.Date eomDate = Freaky.getEOM(monTracker);
            java.sql.Date fomSQL = Freaky.getFOM(monTracker-1);
            java.sql.Date fomDate = Freaky.getActualFOM(monTracker);
            jPanel1.setBorder(BorderFactory.createTitledBorder(Freaky.getMonthDesc(monTracker)));
        
            //lblFirstDay.setText(String.valueOf(fomDate));
            //lblLastDay.setText(String.valueOf(eomDate));
            
            ListTransactionsNew(fomSQL, eomDate);
            RefreshAnalytics(fomDate, fomSQL, eomDate);
            DisplayCatSummary(fomSQL, eomDate);
           
            //int row = (tblLedger.getRowCount() -1);
            //if(row>0){
            //lblEndingBal.setText(String.valueOf(model.getValueAt(row,5)));
            //lblStartingBal.setText(String.valueOf(model.getValueAt(0,5)));
            //}
        }
        if (rdoByWeek.isSelected()){
            
            weekTracker = weekTracker-7;
            java.sql.Date eowDate = Freaky.getEOW(weekTracker-1);
            java.sql.Date fowDate = Freaky.getFOW(weekTracker);
            
            lblFirstDay.setText(String.valueOf(fowDate));
            lblLastDay.setText(String.valueOf(eowDate));
            ListTransactionsNew(fowDate, eowDate);
            
            
            int row = (tblLedger.getRowCount() -1);
            if(row>0){
            lblEndingBal.setText("$ " + String.valueOf(model.getValueAt(row,5)));
            lblStartingBal.setText("$ " + String.valueOf(model.getValueAt(0,5)));
            }
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoryActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fmlUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fmlUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fmlUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fmlUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fmlUi().setVisible(true);
            }
        });
    }
    
    private void RefreshAnalytics(Date dateFrom, Date sqlFrom, Date dateTo){
        
        //Analytics date range
        lblFirstDay.setText(String.valueOf(dateFrom));
        lblLastDay.setText(String.valueOf(dateTo));
      
        int row = (tblLedger.getRowCount() -1);
        if(row>-1){
           //Analytics balances
           lblEndingBal.setText("$ " + String.valueOf(model.getValueAt(row,5)));
           lblStartingBal.setText("$ " + String.valueOf(model.getValueAt(0,5)));
        
           //Analytics summary
           int groceries = 0; int dining = 0; int gas = 0; int unplanned = 0;
           groceries = sqlite.getCategorySum(sqlFrom, dateTo, "Groceries");
           dining = sqlite.getCategorySum(sqlFrom, dateTo, "Dining");
           gas = sqlite.getCategorySum(sqlFrom, dateTo, "Auto");
           unplanned = sqlite.getCategorySum(sqlFrom, dateTo, "Unplanned");
           lblGroceries.setText("$" + String.valueOf((groceries*-1)));
           lblDining.setText("$" + String.valueOf((dining*-1)));
           lblGas.setText("$" + String.valueOf((gas*-1)));
           lblUnplanned.setText("$" + String.valueOf((unplanned*-1)));
        }
        
    }
    
    private void ListTransactionsNew(Date date1, Date dateTo){
      lblCurrentBal.setText("$ " + String.valueOf(sqlite.GetBalance(sqlToday)));
     
      //remove all the rows currently in the table
      model.setRowCount(0);
      
      ArrayList<Transaction> ledgerList = sqlite.getTransactionsByDate(date1, dateTo);
      
      Object rowData[] = new Object[6];
      for(int i = 0; i < ledgerList.size(); i++)
      {
        rowData[0] = ledgerList.get(i).getID();
        rowData[1] = ledgerList.get(i).getDate();
        rowData[2] = ledgerList.get(i).getCategory();
        rowData[3] = ledgerList.get(i).getName();
        rowData[4] = ledgerList.get(i).getAmount();
        rowData[5] = ledgerList.get(i).gettBalance();
        
        model.addRow(rowData); 
      }    
        tblLedger.getColumnModel().getColumn(0).setWidth(0);
        tblLedger.getColumnModel().getColumn(0).setMinWidth(0);
        tblLedger.getColumnModel().getColumn(0).setMaxWidth(0); 
        
        HighLightTableRows(model);
        //DisplayCatSummary();
    }
    
    private void ListTransactions(){
      lblCurrentBal.setText("$ " + String.valueOf(sqlite.GetBalance(sqlToday)));
      
      //remove all the rows currently in the table
      model.setRowCount(0);
      
      ArrayList<Transaction> ledgerList = sqlite.getAllObjects();
      Object rowData[] = new Object[6];
      for(int i = 0; i < ledgerList.size(); i++)
      {
        rowData[0] = ledgerList.get(i).getID();
        rowData[1] = ledgerList.get(i).getDate();
        rowData[2] = ledgerList.get(i).getCategory();
        rowData[3] = ledgerList.get(i).getName();
        rowData[4] = ledgerList.get(i).getAmount();
        rowData[5] = ledgerList.get(i).gettBalance();
        
        model.addRow(rowData); 
      }
      tblLedger.getColumnModel().getColumn(0).setWidth(0);
      tblLedger.getColumnModel().getColumn(0).setMinWidth(0);
      tblLedger.getColumnModel().getColumn(0).setMaxWidth(0); 
      //DisplayCatSummary();
    }
    
    private void DisplayCatSummary(Date fomSQL, Date eomDate){
        
        lblDebug.setText(fomSQL + " - " + eomDate);
        // get the data from SQlite
        //java.sql.Date eomDate = Freaky.getEOM(0);
        //java.sql.Date fomSQL = Freaky.getFOM(-1);
        ArrayList<CatSummary> catList = sqlite.GetCatSummary(fomSQL, eomDate);
        int ai = catList.size();
        String data[][] = new String[ai][2];
        //Object rowData[] = new Object[2];
        if(ai>0){
            for(int i = 0; i < ai; i++){
                for(int j = 0; j < data[i].length; j++){
                    data[i][0] = catList.get(i).getCategory();
                    int temp = catList.get(i).getAmount();
                    data[i][1] = String.valueOf(temp);
                }
            //System.out.println(data[i][0] +  " : " + data[i][1]);
            }
        // first we need to create a ChartModel object
        ChartModel pie = new ChartModel(data);
        
        //now we need to pop up the chart with test data
        final TableChartPopup tcp = new TableChartPopup(pie.tm); 
        JPanelPie.removeAll();
        JPanelPie.add(tcp.GetPieChart());
        JPanelPie.setVisible(true);
        
        } 
        
    }
    
    // this method validates the DE part of the form and 
    // returns PASS or FAIL to caller
    private String validateDE(){
        if(txtAmount.getText().equals("")){
            return "FAIL";}
        else if(txtName.getText().equals("")){
            return "FAIL";}
        else if(cmbCategory.getSelectedIndex() == -1){
            return "FAIL";
        } else if(!(rdoDeposit.isSelected() || rdoExpense.isSelected())){
            return "FAIL";
        } else {
            return "PASS";}
        
    }
    
    private void addTransaction(){
    java.util.Date date;
    
          int amount = Integer.parseInt(txtAmount.getText());
          String name = txtName.getText();
          String category = (String)cmbCategory.getSelectedItem();
          String idate = txtFormatDate.getText();
        
          if (rdoDeposit.isSelected()){}
          else if (rdoExpense.isSelected()){
            amount = amount * -1;}
          else {
              displayMessage("Select Deposit or Expense");
          }
        
          SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yy");
        
          try
          {
            date = formatDate.parse(idate);
            sqlDate = new java.sql.Date(date.getTime());
          }
          catch(ParseException e) {
            displayMessage("addTransaction: Enter date like 04/15/18");
            return;
          }
        
            //add the transaction to db
            lstInsertID = sqlite.insertTran(sqlDate, category, name, amount);
            ListTransactions();
            rdoShowAll.setSelected(true);
            rdoByMonth.setSelected(false);
            rdoByWeek.setSelected(false);
    }
    private void displayMessage(String message){
        lblMessage.setText(message);
        
        Timer t = new Timer(1500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lblMessage.setText(null);
            }
        });
        t.setRepeats(false);
        t.start();
    }
    
    // override table renderer to add customer highlighting to table
    private JComponent HighLightTableRows(DefaultTableModel model)
	{
		JTable table = new JTable( model )
		{
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);

				//  Color row based on a cell value

				if (!isRowSelected(row))
				{
					c.setBackground(getBackground());
					int modelRow = convertRowIndexToModel(row);
					String type = (String)getModel().getValueAt(modelRow, 2);
					if ("Savings".equals(type)) c.setBackground(Color.GREEN);
					if ("Deposit".equals(type)) c.setBackground(Color.YELLOW);
				}

				return c;
			}
		};

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.changeSelection(0, 0, false, false);
        table.setAutoCreateRowSorter(true);
		return new JScrollPane( table );
	}
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelPie;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDoIt;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbRepeater;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBudget;
    private javax.swing.JLabel lblCash;
    private javax.swing.JLabel lblCurrentBal;
    private javax.swing.JLabel lblDebug;
    private javax.swing.JLabel lblDining;
    private javax.swing.JLabel lblEndingBal;
    private javax.swing.JLabel lblFirstDay;
    private javax.swing.JLabel lblGas;
    private javax.swing.JLabel lblGroceries;
    private javax.swing.JLabel lblLastDay;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblStartingBal;
    private javax.swing.JLabel lblUnplanned;
    private javax.swing.JRadioButton rdoByMonth;
    private javax.swing.JRadioButton rdoByWeek;
    private javax.swing.JRadioButton rdoClear;
    private javax.swing.JRadioButton rdoDelete;
    private javax.swing.JRadioButton rdoDeposit;
    private javax.swing.JRadioButton rdoExpense;
    private javax.swing.JRadioButton rdoShowAll;
    private javax.swing.JRadioButton rdoUpdate;
    private javax.swing.JTable tblLedger;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JFormattedTextField txtFormatDate;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
