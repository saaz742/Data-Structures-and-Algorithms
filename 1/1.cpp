#include <iostream>

using namespace std;

int main()
{
    int n = 0;
    int max = 0;
    cin >> n;
    int nums[n];
    int sum = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> nums[i];
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            for (int k = i; k <= j; k++)
            {
                sum += nums[k];
            }
            if (sum > max)
            {
                max = sum;
            }
            sum = 0;
        }
    }
    cout << max;
    return 0;
}
