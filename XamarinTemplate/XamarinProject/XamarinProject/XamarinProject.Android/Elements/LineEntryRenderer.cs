using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Xamarin.Forms;
using XamarinProject;
using XamarinProject.Droid;
using Xamarin.Forms.Platform.Android;
using Android.Graphics.Drawables;

[assembly: ExportRenderer(typeof(LineEntry), typeof(LineEntryRenderer))]
namespace XamarinProject.Droid
{
	public class LineEntryRenderer : EntryRenderer
	{
		protected override void OnElementChanged(ElementChangedEventArgs<Entry> e) {
			base.OnElementChanged(e);
			var UIEntry = (e.NewElement != null) ? (Entry)e.NewElement : (Entry)e.OldElement;

			if (Control != null) {
				// do whatever you want to the UITextField here!
				GradientDrawable gd = new GradientDrawable();
				gd.SetShape(ShapeType.Rectangle);

				gd.SetColor(Android.Graphics.Color.Black);
				gd.SetStroke(7, Android.Graphics.Color.LightGray);
				gd.SetCornerRadius(20.0f);

				UIEntry.BackgroundColor = Xamarin.Forms.Color.Transparent;

				Control.SetTextColor(Android.Graphics.Color.White);
				Control.SetBackground(gd);
				
			}
		}
	}
}