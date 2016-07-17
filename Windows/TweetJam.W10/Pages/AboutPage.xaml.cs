//---------------------------------------------------------------------------
//
// <copyright file="AboutPage.xaml.cs" company="Microsoft">
//    Copyright (C) 2015 by Microsoft Corporation.  All rights reserved.
// </copyright>
//
// <createdOn>7/16/2016 11:50:45 AM</createdOn>
//
//---------------------------------------------------------------------------

using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Navigation;
using TweetJam.ViewModels;

namespace TweetJam.Pages
{
    public sealed partial class AboutPage : Page
    {
        public AboutPage()
        {
            AboutThisAppModel = new AboutThisAppViewModel();

            this.InitializeComponent();
        }

        public AboutThisAppViewModel AboutThisAppModel { get; private set; }
		
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            ShellPage.Current.ShellControl.SelectItem("About");
            base.OnNavigatedTo(e);
        }
    }
}
