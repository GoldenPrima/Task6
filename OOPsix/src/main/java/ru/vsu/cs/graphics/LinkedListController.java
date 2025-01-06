package ru.vsu.cs.graphics;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.vsu.cs.logic.LinkedList;
import ru.vsu.cs.logic.List;

public class LinkedListController {
    private LinkedList<String> linkedList = new LinkedList<>();

    @FXML
    private TextField linkedListField;

    @FXML
    private TextField addIndexField;

    @FXML
    private TextField addElementField;

    @FXML
    private TextField removeIndexField;

    @FXML
    private TextField removeElementField;

    @FXML
    private TextField setIndexField;

    @FXML
    private TextField setElementField;

    @FXML
    private TextField getIndexField;

    @FXML
    private TextField getElementField;

    @FXML
    private TextField containsAnswerField;

    @FXML
    private TextField containsElementField;

    @FXML
    private TextField isEmptyField;

    @FXML
    private TextField sizeField;

    @FXML
    private TextField addAllField;

    @FXML
    private TextField addAllIndexField;

    @FXML
    private TextField reversedField;

    @FXML
    protected void addAction() {
        int index;
        if (addIndexField.getText().isEmpty() || addElementField.getText().isEmpty()) {
            throw new RuntimeException("Одно из полей пустое");
        }
        index = Integer.parseInt(addIndexField.getText());
        linkedList.add(index, addElementField.getText());
        updateList();
    }

    @FXML
    protected void removeAction() {
        if (!removeIndexField.getText().isEmpty() && !removeElementField.getText().isEmpty()) {
            throw new RuntimeException("Введены оба значения для удаления");
        }
        if (removeIndexField.getText().isEmpty()) {
            linkedList.remove(removeElementField.getText());
        } else {
            linkedList.remove(Integer.parseInt(removeIndexField.getText()));
        }
        updateList();
    }

    @FXML
    protected void setAction() {
        if (setIndexField.getText().isEmpty() || setElementField.getText().isEmpty()) {
            throw new RuntimeException("Не указано одно из значений");
        }
        int index = Integer.parseInt(setIndexField.getText());
        String el = setElementField.getText();
        linkedList.set(index, el);
        updateList();
    }

    @FXML
    protected void getAction() {
        getElementField.setText(linkedList.get(Integer.parseInt(getIndexField.getText())));
    }

    @FXML
    protected void containsAction() {
        if (linkedList.contains(containsElementField.getText())) {
            containsAnswerField.setText("true");
        } else {
            containsAnswerField.setText("false");
        }
    }

    @FXML
    protected void isEmptyAction() {
        if (linkedList.isEmpty()) {
            isEmptyField.setText("true");
        } else {
            isEmptyField.setText("false");
        }
    }

    @FXML
    protected void clearAction() {
        linkedList.clear();
        updateList();
    }

    @FXML
    protected void addAllAction() {
        if (addAllIndexField.getText().isEmpty() || addAllField.getText().isEmpty()) {
            throw new RuntimeException("Одно из полей пустое");
        }
        String[] array = addAllField.getText().split(" ");
        linkedList.addAll(Integer.parseInt(addAllIndexField.getText()), toList(array));
        updateList();
    }

    @FXML
    protected void reversedAction() {
        reversedField.setText(linkedList.reversed().toString());
    }

    private void updateList() {
        linkedListField.setText(linkedList.toString());
        sizeField.setText(String.valueOf(linkedList.size()));
    }

    private List<String> toList(String[] array) {
        List<String> list = new LinkedList<>();
        for (String s: array) {
            list.addLast(s);
        }
        return list;
    }
}