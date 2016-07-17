using System;
using System.Threading.Tasks;
using System.Collections.Generic;
using AppStudio.DataProviders;
using AppStudio.DataProviders.Core;
using AppStudio.DataProviders.Twitter;
using AppStudio.Uwp.Actions;
using AppStudio.Uwp.Commands;
using AppStudio.Uwp;
using System.Linq;

using TweetJam.Navigation;
using TweetJam.ViewModels;

namespace TweetJam.Sections
{
    public class BangaloreSection : Section<TwitterSchema>
    {
		private TwitterDataProvider _dataProvider;

		public BangaloreSection()
		{
			_dataProvider = new TwitterDataProvider(new TwitterOAuthTokens
			{
				ConsumerKey = "YmQybsgpUqhV0MApmcqHK49Ss",
                    ConsumerSecret = "f8RpEgjqkB4zwNWFHM33YGq0bGoOu1pdwQegr38y6ZhLz9r2XY",
                    AccessToken = "751682436631629824-UBKxWeYqMxhrfnh41CePnC88t4ttWof",
                    AccessTokenSecret = "5ZsGTBiodCLZm0kt56y2bWVoGpR7d9XDCmxOGnSpqmxGK"
			});
		}

		public override async Task<IEnumerable<TwitterSchema>> GetDataAsync(SchemaBase connectedItem = null)
        {
            var config = new TwitterDataConfig
            {
                QueryType = TwitterQueryType.Search,
                Query = @"#blrcitytraffic"
            };
            return await _dataProvider.LoadDataAsync(config, MaxRecords);
        }

        public override async Task<IEnumerable<TwitterSchema>> GetNextPageAsync()
        {
            return await _dataProvider.LoadMoreDataAsync();
        }

        public override bool HasMorePages
        {
            get
            {
                return _dataProvider.HasMoreItems;
            }
        }

        public override ListPageConfig<TwitterSchema> ListPage
        {
            get 
            {
                return new ListPageConfig<TwitterSchema>
                {
                    Title = "Bangalore",

                    Page = typeof(Pages.BangaloreListPage),

                    LayoutBindings = (viewModel, item) =>
                    {
                        viewModel.Title = item.UserName.ToSafeString();
                        viewModel.SubTitle = item.Text.ToSafeString();
                        viewModel.ImageUrl = ItemViewModel.LoadSafeUrl(item.UserProfileImageUrl.ToSafeString());
                    },
                    DetailNavigation = (item) =>
                    {
                        return new NavInfo
                        {
                            NavigationType = NavType.DeepLink,
                            TargetUri = new Uri(item.Url)
                        };
                    }
                };
            }
        }

        public override DetailPageConfig<TwitterSchema> DetailPage
        {
            get { return null; }
        }
    }
}
