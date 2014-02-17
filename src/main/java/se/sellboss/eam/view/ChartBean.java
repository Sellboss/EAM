package se.sellboss.eam.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ChartBean implements Serializable {

	private MeterGaugeChartModel meterGaugeModel;

	private CartesianChartModel linearModel;

	public ChartBean() {
		createMeterGaugeModel();
		createLinearModel();
		createPieModel();
	}

	public CartesianChartModel getLinearModel() {
		return linearModel;
	}

	private void createMeterGaugeModel() {

		List<Number> intervals = new ArrayList<Number>() {
			{
				add(70);
				add(120);
				add(220);
			}
		};

		meterGaugeModel = new MeterGaugeChartModel(140, intervals);
	}

	private void createLinearModel() {
		linearModel = new CartesianChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");
		series2.setMarkerStyle("diamond");

		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);

		linearModel.addSeries(series1);
		linearModel.addSeries(series2);
	}

	public MeterGaugeChartModel getMeterGaugeModel() {
		return meterGaugeModel;
	}

	public void setMeterGaugeModel(MeterGaugeChartModel meterGaugeModel) {
		this.meterGaugeModel = meterGaugeModel;
	}

	private PieChartModel pieModel;

	public PieChartModel getPieModel() {
		return pieModel;
	}

	private void createPieModel() {
		pieModel = new PieChartModel();

		pieModel.set("Brand 1", 540);
		pieModel.set("Brand 2", 325);
		pieModel.set("Brand 3", 702);
		pieModel.set("Brand 4", 421);
	}
}
