package com.irma;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity {

	int srMaxCount = 9;
	Calendar inward_calendar = Calendar.getInstance();
	Calendar survey_calendar = Calendar.getInstance();
	Calendar verification_calendar = Calendar.getInstance();
	Calendar validation_calendar = Calendar.getInstance();
	
	String title = "Select Date";
	String pref_name = "form", pref_form_count = "formCount";
	boolean changedInwardCalendar = false, changedSurveyCalendar = false, changedVerficationCalendar = false, changedValidationCalendar = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setView();
		setFormNo();
		setDates();
		setTableViews();
		setLinearLayouts();
//		updateView();
	}
	public void setFormNo(){
		TextView tvFormNo = (TextView) findViewById(R.id.tv_formNo);
		if(tvFormNo != null){
			tvFormNo.setText(String.format("%05d", getFormCount()));
		}
		Button btnSubmit = (Button) findViewById(R.id.btn_submit);
		if(btnSubmit != null){
			btnSubmit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getSharedPreferences(pref_name, Context.MODE_PRIVATE).edit().putInt(pref_form_count, getFormCount()+1).commit();
					Intent i = getIntent();
					finish();
					startActivity(i);
				}
			});
		}
	}
	public int getFormCount(){
		SharedPreferences preferences = getSharedPreferences(pref_name, Context.MODE_PRIVATE);
		return preferences.getInt(pref_form_count, 1);
	}
	public void setLinearLayouts(){

		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		LinearLayout membershipContentLayout = (LinearLayout) findViewById(R.id.ll_detail_of_membership_content);
		if(membershipContentLayout != null){
			membershipContentLayout.removeAllViews();
			for(int i=1;i<7;i++){
				View membershipRoview = inflater.inflate(R.layout.membership_row_view, null);
				membershipContentLayout.addView(membershipRoview);
			}
		}
		LinearLayout quantitywiseSelling = (LinearLayout) findViewById(R.id.ll_quantity_wise_selling);
		if(quantitywiseSelling != null){
			quantitywiseSelling.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Cooperative Dairy");
			nameList.add("Private Dairy");
			nameList.add("Producer Company");
			nameList.add("'Dudhia' (Local Milk-man/middle-man)");
			nameList.add("Nearby Spot Market");
			for(int l=0;l<nameList.size();l++){
				View quantitySellingView = inflater.inflate(R.layout.quantity_selling_row_view, null);
				if(quantitySellingView != null){
					TextView sellingName = (TextView) quantitySellingView.findViewById(R.id.tv_selling_point_name);
					if(sellingName != null){
						sellingName.setText(nameList.get(l));
					}
				}
				quantitywiseSelling.addView(quantitySellingView);
			}
			quantitywiseSelling.addView(inflater.inflate(R.layout.quantity_selling_other_row_view, null));
			/*nameList.clear();
			nameList.add("Self-consumption");
			nameList.add("Total");
			for(int l=0;l<nameList.size();l++){
				View quantitySellingView = inflater.inflate(R.layout.quantity_selling_row_view, null);
				if(quantitySellingView != null){
					TextView sellingName = (TextView) quantitySellingView.findViewById(R.id.tv_selling_point_name);
					if(sellingName != null){
						sellingName.setText(nameList.get(l));
					}
				}
				quantitywiseSelling.addView(quantitySellingView);
			}*/
		}
		
		LinearLayout timeSpentQty = (LinearLayout) findViewById(R.id.ll_time_spent_content);
		if(timeSpentQty != null){
			timeSpentQty.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Cleaning of cattle shed");
			nameList.add("Cleaning/care of animals");
			nameList.add("Collect/prepare fodder");
			nameList.add("Prepare cattle feed");
			nameList.add("Feeding animals ");
			nameList.add("Milking animals");
			nameList.add("Pouring milk");
			nameList.add("Grazing cattle");
			nameList.add("Fetching water for cattle");
			nameList.add("Cleaning of milk vessels");
			for(int l=0;l<nameList.size();l++){
				View timeSpentView = inflater.inflate(R.layout.time_spent_row_view, null);
				if(timeSpentView != null){
					TextView srNo = (TextView) timeSpentView.findViewById(R.id.tv_sr_no);
					if(srNo != null){
						srNo.setText((l+1)+"");
					}
					TextView activity = (TextView) timeSpentView.findViewById(R.id.tv_activity);
					if(activity != null){
						activity.setText(nameList.get(l));
					}
				}
				timeSpentQty.addView(timeSpentView);
			}
			/*View otherView = inflater.inflate(R.layout.time_spent_other_row_view, null);
			if(otherView != null){
				TextView srNo = (TextView) otherView.findViewById(R.id.tv_sr_no);
				if(srNo != null){
					srNo.setText((nameList.size()+1)+"");
				}
				timeSpentQty.addView(otherView);	
			}*/
		}
		/*RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_type_of_benfits);
		if(radioGroup != null){
			radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					if(checkedId == R.id.radio_benefit_yes){
						findViewById(R.id.ll_type_of_benefits).setVisibility(View.VISIBLE);
						findViewById(R.id.ll_type_of_benefits_header).setVisibility(View.VISIBLE);
					}else if(checkedId == R.id.radio_benefit_no){
						findViewById(R.id.ll_type_of_benefits).setVisibility(View.GONE);
						findViewById(R.id.ll_type_of_benefits_header).setVisibility(View.GONE);
					}
				}
			});
		}
		LinearLayout benfitsContent = (LinearLayout) findViewById(R.id.ll_type_of_benefits);
		if(benfitsContent != null){
			
			benfitsContent.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Any pension");
			nameList.add("Health insurance");
			nameList.add("Employment benefit");
			nameList.add("Maternity benefits");
			nameList.add("Access to credit under any government scheme");
			nameList.add("Membership of any community associations\n� SHGs, Cooperative Credit Societies,\nLivelihood Group");
			for(int l=0;l<nameList.size();l++){
				View timeSpentView = inflater.inflate(R.layout.type_of_benefits_row_view, null);
				if(timeSpentView != null){
					TextView benefit = (TextView) timeSpentView.findViewById(R.id.tv_benfit);
					if(benefit != null){
						benefit.setText(nameList.get(l));
					}
				}
				benfitsContent.addView(timeSpentView);
			}
		}*/
		LinearLayout animalDetails = (LinearLayout) findViewById(R.id.ll_animal_details);
		if(animalDetails != null){
			
			animalDetails.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Selling milk");
			nameList.add("Vaccination and veterinary services ");
			nameList.add("Cultivation of animal feed ");
			nameList.add("Buying of animal feed ");
			nameList.add("Artificial insemination of the milch animals");
			for(int l=0;l<nameList.size();l++){
				View animalDetailView = inflater.inflate(R.layout.animal_details_row_view, null);
				if(animalDetailView != null){
					TextView benefit = (TextView) animalDetailView.findViewById(R.id.tv_benfit);
					if(benefit != null){
						benefit.setText(nameList.get(l));
					}
				}
				animalDetails.addView(animalDetailView);
			}
		}
		LinearLayout productiveDetails = (LinearLayout) findViewById(R.id.ll_productive_capital);
		if(productiveDetails != null){
			
			productiveDetails.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Milch animals");
			nameList.add("Milk Produced");
			//nameList.add("Dairy related equipments (mechanized)");
			//nameList.add("Dairy related equipments up to Rs. 5000/-");
			for(int l=0;l<nameList.size();l++){
				View productiveDetailView = inflater.inflate(R.layout.productive_capital_row_view, null);
				if(productiveDetailView != null){
					TextView productive = (TextView) productiveDetailView.findViewById(R.id.tv_productive_capital);
					if(productive != null){
						productive.setText(nameList.get(l));
					}
				}
				productiveDetails.addView(productiveDetailView);
			}
		}
        LinearLayout landingSources = (LinearLayout) findViewById(R.id.ll_landing_sources);
        if(landingSources != null){

            landingSources.removeAllViews();
            ArrayList<String> nameList = new ArrayList<String>();
            nameList.add("Non-Governmental Organizations (NGOs)");
            nameList.add("Money Lander");
            nameList.add("Formal Lender\n(Banks Financial Institutional NBFC)");
            nameList.add("Friends or Relative");
            nameList.add("Micro-Finance or Such Group Based Lending Schemes");
            //nameList.add("Dairy related equipments (mechanized)");
            //nameList.add("Dairy related equipments up to Rs. 5000/-");
            for(int l=0;l<nameList.size();l++){
                View productiveDetailView = inflater.inflate(R.layout.productive_capital_row_view, null);
                if(productiveDetailView != null){
                    TextView productive = (TextView) productiveDetailView.findViewById(R.id.tv_productive_capital);
                    if(productive != null){
                        productive.setText(nameList.get(l));
                    }
                }
                landingSources.addView(productiveDetailView);
            }
        }
		LinearLayout groupMembership = (LinearLayout) findViewById(R.id.ll_group_membership);
		if(groupMembership != null){
			
			groupMembership.removeAllViews();
			groupMembership.addView(inflater.inflate(R.layout.group_membership_detail_header_view, null));
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Agriculture/livestock/milk producer or marketing groups");
			nameList.add("Water users' group");
			nameList.add("Forest users' group");
			nameList.add("Credit or micro-finance group");
			nameList.add("Panchayat");

			for(int l=0;l<nameList.size();l++){
				View groupMembershipRow = inflater.inflate(R.layout.group_membership_detail_row_view, null);
				if(groupMembershipRow != null){
					TextView groupType = (TextView) groupMembershipRow.findViewById(R.id.tv_group_type);
					if(groupType != null){
						groupType.setText(nameList.get(l));
					}
				}
				groupMembership.addView(groupMembershipRow);
			}
            groupMembership.addView(inflater.inflate(R.layout.group_membership_details_other_view, null));
		}
		LinearLayout womenEmpowermentWomen = (LinearLayout) findViewById(R.id.ll_women_empowerment_woment);
		if(womenEmpowermentWomen != null){
			
			womenEmpowermentWomen.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Any pension");
			nameList.add("Health insurance");
			nameList.add("Employment benefit");
			nameList.add("Maternity benefits");
			nameList.add("Access to credit under any government scheme");
			nameList.add("Membership of any community associations\n� SHGs, Cooperative Credit Societies,\nLivelihood Group");
			for(int l=0;l<nameList.size();l++){
				View timeSpentView = inflater.inflate(R.layout.type_of_benefits_row_view, null);
				if(timeSpentView != null){
					TextView benefit = (TextView) timeSpentView.findViewById(R.id.tv_benfit);
					if(benefit != null){
						benefit.setText(nameList.get(l));
					}
				}
				womenEmpowermentWomen.addView(timeSpentView);
			}
		}
		RadioGroup radioGroupWomen = (RadioGroup) findViewById(R.id.radio_women_empowerment_woment);
		if(radioGroupWomen != null){
			radioGroupWomen.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					if(checkedId == R.id.radio_women_empowerment_woment_yes){
						findViewById(R.id.ll_women_empowerment_woment).setVisibility(View.VISIBLE);
						findViewById(R.id.ll_women_empowerment_woment_header).setVisibility(View.VISIBLE);
					}else if(checkedId == R.id.radio_women_empowerment_woment_no){
						findViewById(R.id.ll_women_empowerment_woment).setVisibility(View.GONE);
						findViewById(R.id.ll_women_empowerment_woment_header).setVisibility(View.GONE);
					}
				}
			});
		}
		LinearLayout capacityEnhancement = (LinearLayout) findViewById(R.id.ll_capacity_enhancement);
		if(capacityEnhancement != null){
			
			capacityEnhancement.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Did you participate in Diary related �.? ");
			nameList.add("Do you recall as who provided the training");
			nameList.add("Did you understand what was discussed?");
			nameList.add("If Yes, Can you please share what was discussed/shared");
			nameList.add("Did you discuss/ask any questions?");
			nameList.add("Was timing suitable?");
			for(int l=0;l<nameList.size();l++){
				View timeSpentView = inflater.inflate(R.layout.capacity_enhancement_row_view, null);
				if(timeSpentView != null){
					TextView benefit = (TextView) timeSpentView.findViewById(R.id.tv_productive_capital);
					if(benefit != null){
						benefit.setText(nameList.get(l));
					}
				}
				capacityEnhancement.addView(timeSpentView);
				if(l == 0){
					capacityEnhancement.addView(inflater.inflate(R.layout.capacity_enhancement_ask_textview, null));
				}
			}
		}
		LinearLayout recallContent = (LinearLayout) findViewById(R.id.ll_training_recall_content);
		if(recallContent != null){
			
			recallContent.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Animal Nutrition");
			nameList.add("FD");
			nameList.add("Animal health care");
			for(int l=0;l<nameList.size();l++){
				View timeSpentView = inflater.inflate(R.layout.recall_row_view, null);
				if(timeSpentView != null){
					TextView benefit = (TextView) timeSpentView.findViewById(R.id.tv_training_topic);
					if(benefit != null){
						benefit.setText(nameList.get(l));
					}
				}
				recallContent.addView(timeSpentView);
				
			}
		}
		LinearLayout animalFourthDetails = (LinearLayout) findViewById(R.id.ll_fourth_section_animal_details);
		if(animalFourthDetails != null){
			
			animalFourthDetails.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Selling milk");
			nameList.add("Vaccination and veterinary services ");
			nameList.add("Cultivation of animal feed ");
			nameList.add("Buying of animal feed ");
			nameList.add("Artificial insemination of the milch animals");
			for(int l=0;l<nameList.size();l++){
				View animalDetailView = inflater.inflate(R.layout.animal_details_row_view, null);
				if(animalDetailView != null){
					TextView benefit = (TextView) animalDetailView.findViewById(R.id.tv_benfit);
					if(benefit != null){
						benefit.setText(nameList.get(l));
					}
				}
				animalFourthDetails.addView(animalDetailView);
			}
		}
		LinearLayout productiveForthSectionDetails = (LinearLayout) findViewById(R.id.ll_fourth_section_productive_capital);
		if(productiveForthSectionDetails != null){
			
			productiveForthSectionDetails.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Milch animals");
			nameList.add("Milk Produced");
			nameList.add("Dairy related equipments (mechanized)");
			nameList.add("Dairy related equipments up to Rs. 5000/-");
			for(int l=0;l<nameList.size();l++){
				View productiveDetailView = inflater.inflate(R.layout.productive_capital_row_view, null);
				if(productiveDetailView != null){
					TextView productive = (TextView) productiveDetailView.findViewById(R.id.tv_productive_capital);
					if(productive != null){
						productive.setText(nameList.get(l));
					}
				}
				productiveForthSectionDetails.addView(productiveDetailView);
			}
		}
		LinearLayout groupFourthMembership = (LinearLayout) findViewById(R.id.ll_fourth_group_membership);
		if(groupFourthMembership != null){
			
			groupFourthMembership.removeAllViews();
			groupFourthMembership.addView(inflater.inflate(R.layout.group_membership_detail_header_view, null));
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Group Type Agriculture/livestock/milk producer or marketing groups");
			nameList.add("Water users� group");
			nameList.add("Forest users� group");
			nameList.add("Credit or micro-finance group");
			nameList.add("Panchayati raj institutions");

			for(int l=0;l<nameList.size();l++){
				View groupMembershipRow = inflater.inflate(R.layout.group_membership_detail_row_view, null);
				if(groupMembershipRow != null){
					TextView groupType = (TextView) groupMembershipRow.findViewById(R.id.tv_group_type);
					if(groupType != null){
						groupType.setText(nameList.get(l));
					}
				}
				groupFourthMembership.addView(groupMembershipRow);
			}
		}
		LinearLayout timeSpentFourthSectionQty = (LinearLayout) findViewById(R.id.ll_fourth_section_time_spent_content);
		if(timeSpentFourthSectionQty != null){
			timeSpentFourthSectionQty.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("Cleaning of cattle shed");
			nameList.add("Cleaning/care of animals");
			nameList.add("Collect/prepare fodder");
			nameList.add("�Prepare cattle feed");
			nameList.add("Feeding animals ");
			nameList.add("Milking animals");
			nameList.add("Pouring milk");
			nameList.add("Grazing cattle");
			nameList.add("Fetching water for cattle");
			nameList.add("Cleaning of milk vessels");
			for(int l=0;l<nameList.size();l++){
				View timeSpentView = inflater.inflate(R.layout.time_spent_row_view, null);
				if(timeSpentView != null){
					TextView srNo = (TextView) timeSpentView.findViewById(R.id.tv_sr_no);
					if(srNo != null){
						srNo.setText((l+1)+"");
					}
					TextView activity = (TextView) timeSpentView.findViewById(R.id.tv_activity);
					if(activity != null){
						activity.setText(nameList.get(l));
					}
				}
				timeSpentFourthSectionQty.addView(timeSpentView);
			}
			View otherView = inflater.inflate(R.layout.time_spent_other_row_view, null);
			if(otherView != null){
				TextView srNo = (TextView) otherView.findViewById(R.id.tv_sr_no);
				if(srNo != null){
					srNo.setText((nameList.size()+1)+"");
				}
				timeSpentFourthSectionQty.addView(otherView);	
			}
		}
		LinearLayout quantityfourthwiseSelling = (LinearLayout) findViewById(R.id.ll_forth_section_quantity_wise_selling);
		if(quantityfourthwiseSelling != null){
			quantityfourthwiseSelling.removeAllViews();
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("If you have to go to visit your parents/relatives/friends or for any work in the neighbouring villages, do you take permission before going?");
			nameList.add("If you have to buy utensils for your house for cooking, do you have to take permission before purchasing?");
			nameList.add("If you have to visit a doctor in case of any illness or health check up, do you have to take permission before going? ");
			nameList.add("If you have to participate in a cultural fair/mela and it is in a nearby village, do you have to take permission before participating?");
			nameList.add("If there is a meeting in the village for village upliftment or dairying activity or agricultural activity and you want to participate, do you have to take permission?");
			nameList.add("If there is a ritual you follow on a daily basis (eg: going to a temple everyday), for such rituals do you have to take permission before going ?");
			for(int l=0;l<nameList.size();l++){
				View quantitySellingView = inflater.inflate(R.layout.quantity_selling_row_view, null);
				if(quantitySellingView != null){
					TextView sellingName = (TextView) quantitySellingView.findViewById(R.id.tv_selling_point_name);
					if(sellingName != null){
						sellingName.setText(nameList.get(l));
					}
				}
				quantityfourthwiseSelling.addView(quantitySellingView);
			}
			
		}

		LinearLayout dailyActivity = (LinearLayout) findViewById(R.id.ll_daily_activity);
		if(dailyActivity != null){
			
			dailyActivity.removeAllViews();
			dailyActivity.addView(inflater.inflate(R.layout.daily_activity_header_row_view, null));
			for(int i=1;i<7;i++){
				View indexView = inflater.inflate(R.layout.daily_activity_row_view, null);
				if(indexView != null){
					TextView srNo = (TextView) indexView.findViewById(R.id.tv_sr_no);
					if(srNo != null){
						srNo.setText(i+"");
					}
					dailyActivity.addView(indexView);
				}
			}
		}
	}
	public void insertAnimals(LayoutInflater inflater, LinearLayout content, String animalName, int count,boolean showOthers){
		View cowView = inflater.inflate(R.layout.livestock_table_row_view, null);
		if(cowView != null){
			View view1 = cowView.findViewById(R.id.tableRow1);
			if(view1 != null){
				if(count > 1){
					TextView text1 = (TextView) view1.findViewById(R.id.tv_in_no);
					if(text1 != null){
						text1.setText(1+"");
					}	
				}else{
//					view1.setVisibility(View.GONE);
					
					view1.findViewById(R.id.tv_in_no).setVisibility(View.INVISIBLE);
					//view1.findViewById(R.id.et_breed_of_animal).setVisibility(View.GONE);
					view1.findViewById(R.id.et_ownership_of_status).setVisibility(View.GONE);
					if(showOthers){

						EditText status = (EditText) view1.findViewById(R.id.et_in_miltch_status_duration);
						if(status != null){
							status.setHint("Others");
						}
						
//							
					}else{
						view1.findViewById(R.id.et_in_miltch_status_duration).setVisibility(View.INVISIBLE);	
					}
//					view1.findViewById(R.id.et_quantity_of_milk).setVisibility(View.INVISIBLE);
				}
				
			}
			View view2 = cowView.findViewById(R.id.tableRow2);
			if(view2 != null){
				if(count >= 2){
					TextView text2 = (TextView) view2.findViewById(R.id.tv_in_no);
					if(text2 != null){
						text2.setText(2+"");
					}	
				}else{
					view2.setVisibility(View.GONE);
				}
				
			}
			View view3 = cowView.findViewById(R.id.tableRow3);
			if(view3 != null){
				if(count >= 3){
					TextView text3 = (TextView) view3.findViewById(R.id.tv_in_no);
					if(text3 != null){
						text3.setText(3+"");
					}	
				}else{
					view3.setVisibility(View.GONE);
				}
				
			}
			
			View view4 = cowView.findViewById(R.id.tableRow4);
			if(view4 != null){

				if(count >= 4){
					TextView text4 = (TextView) view4.findViewById(R.id.tv_in_no);
					if(text4 != null){
						text4.setText(4+"");
					}	
				}else{
					view4.setVisibility(View.GONE);
				}
				
			}
			View view5 = cowView.findViewById(R.id.tableRow5);
			if(view5 != null){
				if(count >= 5){
					TextView text5 = (TextView) view5.findViewById(R.id.tv_in_no);
					if(text5 != null){
							text5.setText(5+"");
					}
						
				}else{
					view5.setVisibility(View.GONE);
				}
			}
			
			TextView tvAnimalName = (TextView) cowView.findViewById(R.id.tv_animal_name);
			if(tvAnimalName != null){
				tvAnimalName.setText(animalName);
				if(count == 1){
					tvAnimalName.getLayoutParams().width = 500;
				}
			}
			
			content.addView(cowView);
			
		}
		
	}
	public void setTableViews(){
		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

		LinearLayout livestockTable = (LinearLayout) findViewById(R.id.ll_livestock_details);
		if(livestockTable != null){
			livestockTable.removeAllViews();
			livestockTable.addView(inflater.inflate(R.layout.livestock_table_header_row_view, null));
			insertAnimals(inflater, livestockTable, "Cow\n(For Example: Tharpakar, Rathi, Hariyani, Sahival, Kankrej, Gir)", 5, false);
			insertAnimals(inflater, livestockTable, "Buffalo\n(For Example:Murra, Bhadavari, Jafarabadi, Pandharpuri, Surti, Mahesani)", 5, false);
			//insertAnimals(inflater, livestockTable, "Any Other", 4, false);

			insertAnimals(inflater, livestockTable, "Total Milk Produced", 1, false);
			insertAnimals(inflater, livestockTable, "Milk consumed in the HH", 1, false);
			insertAnimals(inflater, livestockTable, "Milk used for selling", 1, false);
			insertAnimals(inflater, livestockTable, "Milk used for any other purpose", 1, true);
		}
		TableLayout familyTable = (TableLayout) findViewById(R.id.tbl_family_members);
		if(familyTable != null){
			familyTable.removeAllViews();
			familyTable.addView(inflater.inflate(R.layout.family_member_table_header_row_view, null));
			for(int i=1;i<=srMaxCount;i++){
				View rowView = inflater.inflate(R.layout.family_member_table_row_view, null);
				if(rowView != null){
					TextView sr = (TextView) rowView.findViewById(R.id.tv_sr);
					if(sr != null){
						sr.setText(i+"");
					}
					
				}
				familyTable.addView(rowView);
			}
		}
		TableLayout assetTable = (TableLayout) findViewById(R.id.tbl_assets);
		if(assetTable != null){
			assetTable.removeAllViews();
			assetTable.addView(inflater.inflate(R.layout.asset_header, null));
			ArrayList<String> nameArray = new ArrayList<String>();
			nameArray.add("Agricultural Land");
            nameArray.add("Wasteland Land");
            nameArray.add("Forest Land");
            for(int l=0;l<nameArray.size();l++){
                View assetView = inflater.inflate(R.layout.asset_row_view_land, null);
                if(assetView != null){
                    TextView assetName = (TextView) assetView.findViewById(R.id.tv_asset_name);
                    if(assetName != null){
                        assetName.setText(nameArray.get(l));
                    }
                    assetTable.addView(assetView);
                }
            }
            nameArray.clear();
            nameArray.add("Shop");
            nameArray.add("Agricultural\nequipment");
			nameArray.add("House");
			nameArray.add("Livestock");
			nameArray.add("2 Wheelers");
			nameArray.add("Tractor");
			for(int l=0;l<nameArray.size();l++){
				View assetView = inflater.inflate(R.layout.asset_row_view, null);
				if(assetView != null){
					TextView assetName = (TextView) assetView.findViewById(R.id.tv_asset_name);
					if(assetName != null){
						assetName.setText(nameArray.get(l));
					}
					assetTable.addView(assetView);
				}	
			}
			assetTable.addView(inflater.inflate(R.layout.asset_other_row_view, null));
		}
		/*TableLayout landTable = (TableLayout) findViewById(R.id.tbl_land_details);
		if(landTable != null){
			landTable.removeAllViews();
			landTable.addView(inflater.inflate(R.layout.land_header, null));
			ArrayList<String> nameArray = new ArrayList<String>();
			nameArray.add("Cultivated");
			nameArray.add("Wasteland");
			nameArray.add("Forest Land");
			for(int l=0;l<nameArray.size();l++){
				View cultivatedView = inflater.inflate(R.layout.land_row_view, null);
				if(cultivatedView != null){
					TextView assetName = (TextView) cultivatedView.findViewById(R.id.tv_land_name);
					if(assetName != null){
						assetName.setText(nameArray.get(l));
					}
					landTable.addView(cultivatedView);
				}
			}
			landTable.addView(inflater.inflate(R.layout.land_other_row_view, null));
		}*/
		/*TableLayout typeOfToiletTable = (TableLayout) findViewById(R.id.tbl_type_of_toilet);
		if(typeOfToiletTable != null){
			typeOfToiletTable.removeAllViews();
			typeOfToiletTable.addView(inflater.inflate(R.layout.type_of_toilet_header_row_view, null));
			ArrayList<String> nameArray = new ArrayList<String>();
			nameArray.add("Male");
			nameArray.add("Female");
			nameArray.add("Both");
			for(int l=0;l<nameArray.size();l++){

				View genderView = inflater.inflate(R.layout.type_of_toilet_row_view, null);
				if(genderView != null){
					TextView maleText = (TextView) genderView.findViewById(R.id.tv_gender_text);
					maleText.setText(nameArray.get(l));
					typeOfToiletTable.addView(genderView);
				}
			}
		}*/

		TableLayout incomeSourceTable = (TableLayout) findViewById(R.id.tbl_income_source_detail);
		if(incomeSourceTable != null){
			incomeSourceTable.removeAllViews();
			incomeSourceTable.addView(inflater.inflate(R.layout.income_header, null));
			ArrayList<String> nameArray = new ArrayList<String>();
			nameArray.add("Average Annual Agriculture Income");
			nameArray.add("Average Monthly Income from Poultry");
			nameArray.add("Average Income from Dairy");
			nameArray.add("Average Monthly Income from Agricultural labour");
			nameArray.add("Average Monthly Income from Micro enterprise");
			nameArray.add("Average Monthly Income fromSalaried Job");
			for(int l=0;l<nameArray.size();l++){

				View agricultureView = inflater.inflate(R.layout.income_row_view, null);
				if(agricultureView != null){
					TextView incomeText = (TextView) agricultureView.findViewById(R.id.tv_income_source);
					incomeText.setText(nameArray.get(l));
					incomeSourceTable.addView(agricultureView);
				}
			}
			
			/*for(int i=1;i<5;i++){
				View otherIncome = inflater.inflate(R.layout.income_other_row_view, null);
				if(otherIncome != null){
					EditText currentOther = (EditText) otherIncome.findViewById(R.id.et_other_income_source);
					if(currentOther != null){
						currentOther.setHint("Others "+i);
					}
					incomeSourceTable.addView(otherIncome);
				}	
			}*/
			
		}
		
	}
	public void setView(){
//		TextView education = (TextView) findViewById(R.id.tv_education_header);
//		if(education != null){
//
//			SpannableString content = new SpannableString(education.getText().toString());
//			content.setSpan(new UnderlineSpan(), 0, education.getText().toString().length(), 0);
//			education.setText(content);
//		}
//		TextView occupation = (TextView) findViewById(R.id.tv_primary_occupation_header);
//		if(occupation != null){
//
//			SpannableString content = new SpannableString(occupation.getText().toString());
//			content.setSpan(new UnderlineSpan(), 0, occupation.getText().toString().length(), 0);
//			occupation.setText(content);
//		}
		RadioGroup radioBPL = (RadioGroup) findViewById(R.id.radio_bpl_group);
		if(radioBPL != null){
			radioBPL.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					if(checkedId == R.id.radio_bpl_yes){
						findViewById(R.id.et_bpl_number).setVisibility(View.VISIBLE);
					}else{
						findViewById(R.id.et_bpl_number).setVisibility(View.GONE);
					}
				}
			});
		}
		
	}
	public void setDates(){
		Button btnSurveyDate = (Button) findViewById(R.id.btn_survey_date);
		if(btnSurveyDate != null){
			btnSurveyDate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					final Dialog dialog = new Dialog(MainActivity.this);

					dialog.setContentView(R.layout.date_time_picker);
					DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker1);
					if(datePicker != null){
						datePicker.init(survey_calendar.get(Calendar.YEAR), survey_calendar.get(Calendar.MONTH), survey_calendar.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
							
							@Override
							public void onDateChanged(DatePicker view, int year, int monthOfYear,
									int dayOfMonth) {
								// TODO Auto-generated method stub
								survey_calendar.set(Calendar.YEAR, year);
								survey_calendar.set(Calendar.MONTH, monthOfYear);
								survey_calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								updateView();
							}
						});
					}
					Button b = (Button) dialog.findViewById(R.id.btn_done);
					if(b != null){
						b.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
					}
					dialog.setTitle(title);
					dialog.show();

					changedSurveyCalendar = true;
					updateView();
				}
			});
		}
		Button btnValidationDate = (Button) findViewById(R.id.btn_validation_date);
		if(btnValidationDate != null){
			btnValidationDate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					final Dialog dialog = new Dialog(MainActivity.this);

					dialog.setContentView(R.layout.date_time_picker);
					DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker1);
					if(datePicker != null){
						datePicker.init(validation_calendar.get(Calendar.YEAR), validation_calendar.get(Calendar.MONTH), validation_calendar.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
							
							@Override
							public void onDateChanged(DatePicker view, int year, int monthOfYear,
									int dayOfMonth) {
								// TODO Auto-generated method stub
								validation_calendar.set(Calendar.YEAR, year);
								validation_calendar.set(Calendar.MONTH, monthOfYear);
								validation_calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								updateView();
							}
						});
					}
					Button b = (Button) dialog.findViewById(R.id.btn_done);
					if(b != null){
						b.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
					}
					dialog.setTitle(title);
					dialog.show();

					changedValidationCalendar = true;
					updateView();
				}
			});
		}
		Button btnVerificationDate = (Button) findViewById(R.id.btn_verfication_date);
		if(btnVerificationDate != null){
			btnVerificationDate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					final Dialog dialog = new Dialog(MainActivity.this);

					dialog.setContentView(R.layout.date_time_picker);
					DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker1);
					if(datePicker != null){
						datePicker.init(verification_calendar.get(Calendar.YEAR), verification_calendar.get(Calendar.MONTH), verification_calendar.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
							
							@Override
							public void onDateChanged(DatePicker view, int year, int monthOfYear,
									int dayOfMonth) {
								// TODO Auto-generated method stub
								verification_calendar.set(Calendar.YEAR, year);
								verification_calendar.set(Calendar.MONTH, monthOfYear);
								verification_calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								updateView();
							}
						});
					}
					Button b = (Button) dialog.findViewById(R.id.btn_done);
					if(b != null){
						b.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
					}
					dialog.setTitle(title);
					dialog.show();

					changedVerficationCalendar = true;
					updateView();
				}
			});
		}
		Button btnInwardDate = (Button) findViewById(R.id.btn_inward_date);
		if(btnInwardDate != null){
			btnInwardDate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					final Dialog dialog = new Dialog(MainActivity.this);

					dialog.setContentView(R.layout.date_time_picker);
					DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker1);
					if(datePicker != null){
						datePicker.init(inward_calendar.get(Calendar.YEAR), inward_calendar.get(Calendar.MONTH), inward_calendar.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
							
							@Override
							public void onDateChanged(DatePicker view, int year, int monthOfYear,
									int dayOfMonth) {
								// TODO Auto-generated method stub
								inward_calendar.set(Calendar.YEAR, year);
								inward_calendar.set(Calendar.MONTH, monthOfYear);
								inward_calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								updateView();
							}
						});
					}
					Button b = (Button) dialog.findViewById(R.id.btn_done);
					if(b != null){
						b.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
					}
					dialog.setTitle(title);
					dialog.show();

					changedInwardCalendar = true;
					updateView();
				}
			});
		}
	}
	public void updateView(){

		SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-yyyy");
		Button btnInwardDate = (Button) findViewById(R.id.btn_inward_date);
		if(btnInwardDate != null){
			if(changedInwardCalendar)
			btnInwardDate.setText(ft.format(inward_calendar.getTime()));
		}

		Button btnVerificationDate = (Button) findViewById(R.id.btn_verfication_date);
		if(btnVerificationDate != null){
			if(changedVerficationCalendar)
			btnVerificationDate.setText(ft.format(verification_calendar.getTime()));
		}

		Button btnValidationDate = (Button) findViewById(R.id.btn_validation_date);
		if(btnValidationDate != null){
			if(changedValidationCalendar)
			btnValidationDate.setText(ft.format(validation_calendar.getTime()));
		}

		Button btnSurveyDate = (Button) findViewById(R.id.btn_survey_date);
		if(btnSurveyDate != null){
			if(changedSurveyCalendar)
			btnSurveyDate.setText(ft.format(survey_calendar.getTime()));
		}
	}
	public void numberOfCattlesChanged(View v) {

		RadioButton radio_0 = (RadioButton) findViewById(R.id.no_of_cattles_0);
		if(radio_0 != null){
			if (v.getId() != R.id.no_of_cattles_0) {
				radio_0.setChecked(false);
			}else{
				radio_0.setChecked(true);
			}
		}
		
		RadioButton radio_1 = (RadioButton) findViewById(R.id.no_of_cattles_1);
		if(radio_1 != null){
			if (v.getId() != R.id.no_of_cattles_1) {
				radio_1.setChecked(false);
			}else{
				radio_1.setChecked(true);
			}
		}
		RadioButton radio_2 = (RadioButton) findViewById(R.id.no_of_cattles_2);
		if(radio_2 != null){
			if (v.getId() != R.id.no_of_cattles_2) {
				radio_2.setChecked(false);
			}else{
				radio_2.setChecked(true);
			}
		}
		RadioButton radio_3 = (RadioButton) findViewById(R.id.no_of_cattles_3);
		if(radio_3 != null){
			if (v.getId() != R.id.no_of_cattles_3) {
				radio_3.setChecked(false);
			}else{
				radio_3.setChecked(true);
			}
		}
		RadioButton radio_4 = (RadioButton) findViewById(R.id.no_of_cattles_4);
		if(radio_4 != null){
			if (v.getId() != R.id.no_of_cattles_4) {
				radio_4.setChecked(false);
			}else{
				radio_4.setChecked(true);
			}
		}
		RadioButton radio_5 = (RadioButton) findViewById(R.id.no_of_cattles_5);
		if(radio_5 != null){
			if (v.getId() != R.id.no_of_cattles_5) {
				radio_5.setChecked(false);
			}else{
				radio_5.setChecked(true);
			}
		}
		RadioButton radio_6 = (RadioButton) findViewById(R.id.no_of_cattles_6_or_more);
		if(radio_6 != null){
			if (v.getId() != R.id.no_of_cattles_6_or_more) {
				radio_6.setChecked(false);
			}else{
				radio_6.setChecked(true);
			}
		}
	}

	public void religiousChanged(View v) {

		RadioButton radio_0 = (RadioButton) findViewById(R.id.radio_hindu);
		if(radio_0 != null){
			if (v.getId() != R.id.radio_hindu) {
				radio_0.setChecked(false);
			}else{
				radio_0.setChecked(true);
			}
		}
		
		RadioButton radio_1 = (RadioButton) findViewById(R.id.radio_christian);
		if(radio_1 != null){
			if (v.getId() != R.id.radio_christian) {
				radio_1.setChecked(false);
			}else{
				radio_1.setChecked(true);
			}
		}
		RadioButton radio_2 = (RadioButton) findViewById(R.id.radio_muslim);
		if(radio_2 != null){
			if (v.getId() != R.id.radio_muslim) {
				radio_2.setChecked(false);
			}else{
				radio_2.setChecked(true);
			}
		}
		RadioButton radio_3 = (RadioButton) findViewById(R.id.radio_sikh);
		if(radio_3 != null){
			if (v.getId() != R.id.radio_sikh) {
				radio_3.setChecked(false);
			}else{
				radio_3.setChecked(true);
			}
		}
		RadioButton radio_4 = (RadioButton) findViewById(R.id.radio_other);
		if(radio_4 != null){
			if (v.getId() != R.id.radio_other) {
				radio_4.setChecked(false);
			}else{
				radio_4.setChecked(true);
			}
		}
		RadioButton radio_5 = (RadioButton) findViewById(R.id.no_of_cattles_5);
		if(radio_5 != null){
			if (v.getId() != R.id.no_of_cattles_5) {
				radio_5.setChecked(false);
			}else{
				radio_5.setChecked(true);
			}
		}
	}
	
	public void roadTypeChanged(View v) {

		RadioButton radio_0 = (RadioButton) findViewById(R.id.radio_fully_pucca);
		if(radio_0 != null){
			if (v.getId() != R.id.radio_fully_pucca) {
				radio_0.setChecked(false);
			}else{
				radio_0.setChecked(true);
			}
		}
		
		RadioButton radio_1 = (RadioButton) findViewById(R.id.radio_fully_kuchcha);
		if(radio_1 != null){
			if (v.getId() != R.id.radio_fully_kuchcha) {
				radio_1.setChecked(false);
			}else{
				radio_1.setChecked(true);
			}
		}
		RadioButton radio_2 = (RadioButton) findViewById(R.id.radio_semi_pucca);
		if(radio_2 != null){
			if (v.getId() != R.id.radio_semi_pucca) {
				radio_2.setChecked(false);
			}else{
				radio_2.setChecked(true);
			}
		}

	}
	public void castChanged(View v) {

		RadioButton radio_0 = (RadioButton) findViewById(R.id.radio_general);
		if(radio_0 != null){
			if (v.getId() != R.id.radio_general) {
				radio_0.setChecked(false);
			}else{
				radio_0.setChecked(true);
			}
		}
		
		RadioButton radio_1 = (RadioButton) findViewById(R.id.radio_sc);
		if(radio_1 != null){
			if (v.getId() != R.id.radio_sc) {
				radio_1.setChecked(false);
			}else{
				radio_1.setChecked(true);
			}
		}
		RadioButton radio_2 = (RadioButton) findViewById(R.id.radio_obc);
		if(radio_2 != null){
			if (v.getId() != R.id.radio_obc) {
				radio_2.setChecked(false);
			}else{
				radio_2.setChecked(true);
			}
		}
		RadioButton radio_3 = (RadioButton) findViewById(R.id.radio_st);
		if(radio_3 != null){
			if (v.getId() != R.id.radio_st) {
				radio_3.setChecked(false);
			}else{
				radio_3.setChecked(true);
			}
		}
	}
}
