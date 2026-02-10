package com.carManager.frontend.views.car;

import com.carManager.frontend.dto.CarDTO;
import com.carManager.frontend.service.CarRestService;
import com.carManager.frontend.views.BaseView;
import com.carManager.frontend.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "cars", layout = MainLayout.class)
@PageTitle("Cars")
public class CarListView extends BaseView {

    private final CarRestService carService;
    private final Grid<CarDTO> grid = new Grid<>(CarDTO.class, false);

    public CarListView(CarRestService carService) {
        this.carService = carService;

        setSizeFull();
        configureGrid();
        add(grid);
        loadCars();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(CarDTO::id).setHeader("ID").setSortable(true);
        grid.addColumn(CarDTO::licensePlate).setHeader("License Plate").setSortable(true);
        grid.addColumn(CarDTO::seatCount).setHeader("Seats").setSortable(true);
        grid.addColumn(CarDTO::convertible).setHeader("Convertible").setSortable(true);
        grid.addColumn(CarDTO::rating).setHeader("Rating").setSortable(true);
        grid.addColumn(car -> car.engineType() != null ? car.engineType().name() : "")
            .setHeader("Engine Type").setSortable(true);
        grid.addColumn(car -> car.dateCreated() != null ? car.dateCreated().toLocalDate().toString() : "")
            .setHeader("Date Created").setSortable(true);

        grid.addComponentColumn(car -> {
            TextField licensePlateField = new TextField();
            licensePlateField.setValue(car.licensePlate() != null ? car.licensePlate() : "");
            licensePlateField.setWidth("150px");

            Button updateBtn = new Button("Update", e -> {
                if (licensePlateField.isEmpty()) {
                    showError("License plate cannot be empty");
                    return;
                }
                try {
                    carService.updateLicensePlate(car.id(), licensePlateField.getValue());
                    showSuccess("License plate updated");
                    loadCars();
                } catch (Exception ex) {
                    showError("Update failed: " + ex.getMessage());
                }
            });
            updateBtn.addThemeVariants(ButtonVariant.LUMO_SMALL);

            HorizontalLayout layout = new HorizontalLayout(licensePlateField, updateBtn);
            layout.setAlignItems(Alignment.CENTER);
            return layout;
        }).setHeader("Update License Plate").setWidth("300px");
    }

    private void loadCars() {
        try {
            grid.setItems(carService.getAllCars());
        } catch (Exception ex) {
            showError("Failed to load cars: " + ex.getMessage());
        }
    }
}
